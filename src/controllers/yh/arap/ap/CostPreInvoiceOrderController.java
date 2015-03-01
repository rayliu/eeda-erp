package controllers.yh.arap.ap;

import interceptor.SetAttrLoginUserInterceptor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.ArapCostInvoiceApplication;
import models.ArapCostInvoiceItemInvoiceNo;
import models.ArapCostOrder;
import models.ArapCostOrderInvoiceNo;
import models.Party;
import models.UserLogin;
import models.yh.profile.Contact;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import controllers.yh.util.OrderNoGenerator;
import controllers.yh.util.OrderNoUtil;
import controllers.yh.util.PermissionConstant;

@RequiresAuthentication
@Before(SetAttrLoginUserInterceptor.class)
public class CostPreInvoiceOrderController extends Controller {
    private Logger logger = Logger.getLogger(CostPreInvoiceOrderController.class);
	Subject currentUser = SecurityUtils.getSubject();
	@RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_LIST})
    public void index() {
	    render("/yh/arap/CostPreInvoiceOrder/CostPreInvoiceOrderList.html");
    }


    public void confirm() {
        String ids = getPara("ids");
        String[] idArray = ids.split(",");
        logger.debug(String.valueOf(idArray.length));

        String customerId = getPara("customerId");
        Party party = Party.dao.findById(customerId);

        Contact contact = Contact.dao.findById(party.get("contact_id").toString());
        setAttr("customer", contact);
    	setAttr("type", "CUSTOMER");
    	setAttr("classify", "receivable");
        render("/yh/arap/ChargeAcceptOrder/ChargeCheckOrderEdit.html");
    }

    // 应付条目列表
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_LIST})
    public void list() {
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }
        String sp = getPara("sp");
        String customer = getPara("customer");
        String orderNo = getPara("orderNo");
        String beginTime = getPara("beginTime");
        String endTime = getPara("endTime");
        
        String sqlTotal = "";
        String sql = "select aaia.*,ul.user_name create_by,ul2.user_name audit_by,ul3.user_name approval_by from arap_cost_invoice_application_order aaia "
	    			+ " left join user_login ul on ul.id = aaia.create_by"
	    			+ " left join user_login ul2 on ul2.id = aaia.audit_by"
	    			+ " left join user_login ul3 on ul3.id = aaia.approver_by ";
        String condition = "";
        //TODO 客户和供应商没做
        if(sp != null || customer != null || orderNo != null 
        		|| beginTime != null || endTime != null){
        	if (beginTime == null || "".equals(beginTime)) {
				beginTime = "1-1-1";
			}
			if (endTime == null || "".equals(endTime)) {
				endTime = "9999-12-31";
			}
			condition = " where ifnull(aaia.order_no,'') like '%" + orderNo + "%' "
					+ " and aaia.create_stamp between '" + beginTime + "' and '" + endTime + "' "; 
			
			
        }
        
        sqlTotal = "select count(1) total from arap_cost_invoice_application_order aaia "
        		+ " left join user_login ul on ul.id = aaia.create_by"
    			+ " left join user_login ul2 on ul2.id = aaia.audit_by"
    			+ " left join user_login ul3 on ul3.id = aaia.approver_by ";
       
        sql =  sql + condition + " order by aaia.create_stamp desc " + sLimit;
       
        Record rec = Db.findFirst(sqlTotal + condition );
        logger.debug("total records:" + rec.getLong("total"));


        List<Record> BillingOrders = Db.find(sql);

        Map BillingOrderListMap = new HashMap();
        BillingOrderListMap.put("sEcho", pageIndex);
        BillingOrderListMap.put("iTotalRecords", rec.getLong("total"));
        BillingOrderListMap.put("iTotalDisplayRecords", rec.getLong("total"));

        BillingOrderListMap.put("aaData", BillingOrders);

        renderJson(BillingOrderListMap);
    }
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_CREATE})
	public void create() {
		String ids = getPara("ids");
		setAttr("costCheckOrderIds", ids);
		Double totalAmount = 0.0;
		if(ids != null && !"".equals(ids)){
			String[] idArray = ids.split(",");
			ArapCostOrder arapCostOrder = ArapCostOrder.dao.findById(idArray[0]);
			Long spId = arapCostOrder.getLong("payee_id");
			if (!"".equals(spId) && spId != null) {
				Party party = Party.dao.findById(spId);
				setAttr("party", party);
				Contact contact = Contact.dao.findById(party.get("contact_id").toString());
				setAttr("customer", contact);
			}
			for(int i=0;i<idArray.length;i++){
				arapCostOrder = ArapCostOrder.dao.findById(idArray[i]);
				Double costCheckAmount = arapCostOrder.getDouble("cost_amount")==null?0.0:arapCostOrder.getDouble("cost_amount");
				totalAmount = totalAmount + costCheckAmount;
			}
		}

		setAttr("saveOK", false);
		setAttr("totalAmount", totalAmount);
		String name = (String) currentUser.getPrincipal();
		List<UserLogin> users = UserLogin.dao
				.find("select * from user_login where user_name='" + name + "'");
		setAttr("create_by", users.get(0).get("id"));
	
		UserLogin userLogin = UserLogin.dao.findById(users.get(0).get("id"));
		setAttr("userLogin", userLogin);

		setAttr("status", "new");
			render("/yh/arap/CostPreInvoiceOrder/CostPreInvoiceOrderEdit.html");
	}
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_CREATE, PermissionConstant.PERMSSION_CPO_UPDATE}, logical=Logical.OR)
	public void save() {
		ArapCostInvoiceApplication arapAuditInvoiceApplication = null;
		String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
		String paymentMethod = getPara("paymentMethod");
		if (!"".equals(costPreInvoiceOrderId) && costPreInvoiceOrderId != null) {
			arapAuditInvoiceApplication = ArapCostInvoiceApplication.dao.findById(costPreInvoiceOrderId);
			arapAuditInvoiceApplication.set("status", "new");
			arapAuditInvoiceApplication.set("create_by", getPara("create_by"));
			arapAuditInvoiceApplication.set("create_stamp", new Date());
			arapAuditInvoiceApplication.set("remark", getPara("remark"));
			arapAuditInvoiceApplication.set("last_modified_by", getPara("create_by"));
			arapAuditInvoiceApplication.set("last_modified_stamp", new Date());
			arapAuditInvoiceApplication.set("payment_method", paymentMethod);
			if("transfers".equals(paymentMethod)){
				if(getPara("accountTypeSelect") != null && !"".equals(getPara("accountTypeSelect"))){
					arapAuditInvoiceApplication.set("account_id", getPara("accountTypeSelect"));
				}
			}else{
				arapAuditInvoiceApplication.set("account_id", null);				
			}
			arapAuditInvoiceApplication.update();
		} else {
			String payee_id = getPara("customer_id");
			if(payee_id == null || "".equals(payee_id)){
				payee_id = null;
			}
			arapAuditInvoiceApplication = new ArapCostInvoiceApplication();
			String sql = "select * from arap_cost_invoice_application_order order by id desc limit 0,1";
			arapAuditInvoiceApplication.set("order_no", OrderNoGenerator.getNextOrderNo("YFSQ"));
			arapAuditInvoiceApplication.set("status", "新建");
			arapAuditInvoiceApplication.set("payee_id", payee_id);
			arapAuditInvoiceApplication.set("create_by", getPara("create_by"));
			arapAuditInvoiceApplication.set("create_stamp", new Date());
			arapAuditInvoiceApplication.set("remark", getPara("remark"));
			arapAuditInvoiceApplication.set("payment_method", getPara("paymentMethod"));
			if(getPara("total_amount") != null && !"".equals(getPara("total_amount"))){
				arapAuditInvoiceApplication.set("total_amount", getPara("total_amount"));				
			}
			if("transfers".equals(paymentMethod)){
				if(getPara("accountTypeSelect") != null && !"".equals(getPara("accountTypeSelect"))){
					arapAuditInvoiceApplication.set("account_id", getPara("accountTypeSelect"));
				}
			}else{
				arapAuditInvoiceApplication.set("account_id", null);				
			}
			arapAuditInvoiceApplication.save();

			String costCheckOrderIds = getPara("costCheckOrderIds");
			String[] costCheckOrderIdsArr = costCheckOrderIds.split(",");
			for (int i = 0; i < costCheckOrderIdsArr.length; i++) {
				ArapCostOrder arapAuditOrder = ArapCostOrder.dao.findById(costCheckOrderIdsArr[i]);
				arapAuditOrder.set("application_order_id", arapAuditInvoiceApplication.get("id"));
				arapAuditOrder.set("status", "付款申请中");
				arapAuditOrder.update();
			}
		}
		renderJson(arapAuditInvoiceApplication);;
	}
	
	// 审核

@RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_APPROVAL})
	public void auditCostPreInvoiceOrder(){
		String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
		if(costPreInvoiceOrderId != null && !"".equals(costPreInvoiceOrderId)){
			ArapCostInvoiceApplication arapAuditOrder = ArapCostInvoiceApplication.dao.findById(costPreInvoiceOrderId);
			arapAuditOrder.set("status", "已审核");
            String name = (String) currentUser.getPrincipal();
			List<UserLogin> users = UserLogin.dao.find("select * from user_login where user_name='" + name + "'");
			arapAuditOrder.set("audit_by", users.get(0).get("id"));
			arapAuditOrder.set("audit_stamp", new Date());
			arapAuditOrder.update();
		}
        renderJson("{\"success\":true}");
	}
	
	// 审批
	@RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_CONFIRMATION})
	public void approvalCostPreInvoiceOrder(){
		String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
		if(costPreInvoiceOrderId != null && !"".equals(costPreInvoiceOrderId)){
			ArapCostInvoiceApplication arapAuditOrder = ArapCostInvoiceApplication.dao.findById(costPreInvoiceOrderId);
			arapAuditOrder.set("status", "已审批");
            String name = (String) currentUser.getPrincipal();
			List<UserLogin> users = UserLogin.dao.find("select * from user_login where user_name='" + name + "'");
			arapAuditOrder.set("approver_by", users.get(0).get("id"));
			arapAuditOrder.set("approval_stamp", new Date());
			arapAuditOrder.update();
		}
		renderJson("{\"success\":true}");
	}
	@RequiresPermissions(value = {PermissionConstant.PERMSSION_CPO_UPDATE})
	public void edit() throws ParseException {
		String id = getPara("id");
		ArapCostInvoiceApplication arapAuditInvoiceApplication = ArapCostInvoiceApplication.dao.findById(id);
		Long customerId = arapAuditInvoiceApplication.get("payee_id");
		if (!"".equals(customerId) && customerId != null) {
			Party party = Party.dao.findById(customerId);
			setAttr("party", party);
			Contact contact = Contact.dao.findById(party.get("contact_id").toString());
			setAttr("customer", contact);
		}
		UserLogin userLogin = UserLogin.dao.findById(arapAuditInvoiceApplication.get("create_by"));
		setAttr("userLogin", userLogin);
		setAttr("arapAuditInvoiceApplication", arapAuditInvoiceApplication);

		String costCheckOrderIds = "";
		List<ArapCostOrder> arapCostOrders = ArapCostOrder.dao.find("select * from arap_cost_order where application_order_id = ?", id);
		for(ArapCostOrder arapCostOrder : arapCostOrders){
			costCheckOrderIds += arapCostOrder.get("id") + ",";
		}
		costCheckOrderIds = costCheckOrderIds.substring(0, costCheckOrderIds.length() - 1);
		setAttr("costCheckOrderIds", costCheckOrderIds);
			render("/yh/arap/CostPreInvoiceOrder/CostPreInvoiceOrderEdit.html");
	}
	
    // 添加发票
    public void addInvoiceItem(){
    	String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
    	ArapCostInvoiceApplication application = ArapCostInvoiceApplication.dao.findById(costPreInvoiceOrderId);
    	if(costPreInvoiceOrderId != null && !"".equals(costPreInvoiceOrderId)){
    		ArapCostInvoiceItemInvoiceNo arapCostInvoiceItemInvoiceNo = new ArapCostInvoiceItemInvoiceNo();
    		arapCostInvoiceItemInvoiceNo.set("invoice_id", costPreInvoiceOrderId);
    		if(application.get("payee_id") != null && !"".equals(application.get("payee_id"))){
    			arapCostInvoiceItemInvoiceNo.set("payee_id", application.get("payee_id"));
    		}
    		arapCostInvoiceItemInvoiceNo.save();
    	}
        renderJson("{\"success\":true}");
    }
    
    // 发票号列表
    public void costInvoiceItemList(){
    	String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }
        Map orderMap = new HashMap();
        // 获取总条数
        String totalWhere = "";
        String sql = "select count(1) total from arap_cost_invoice_item_invoice_no acio"
				+ " left join arap_cost_order_invoice_no  acoi on acoi.invoice_no = acio.invoice_no"
				+ " where acio.invoice_id = " + costPreInvoiceOrderId;
        Record rec = Db.findFirst(sql + totalWhere);
        logger.debug("total records:" + rec.getLong("total"));

        // 获取当前页的数据
        List<Record> orders = Db.find("select distinct acio.*,c.abbr cname,(select group_concat(aco.order_no separator '\r\n') from arap_cost_order aco left join arap_cost_order_invoice_no app_no on app_no.cost_order_id = aco.id where app_no.invoice_no = acio.invoice_no) cost_order_no "
        		+ " from arap_cost_invoice_item_invoice_no acio"
				+ " left join arap_cost_order_invoice_no  acoi on acoi.invoice_no = acio.invoice_no"
				+ " left join party p on p.id = acio.payee_id left join contact c on c.id = p.contact_id"
				+ " where acio.invoice_id = " + costPreInvoiceOrderId + " " + sLimit);

        orderMap.put("sEcho", pageIndex);
        orderMap.put("iTotalRecords", rec.getLong("total"));
        orderMap.put("iTotalDisplayRecords", rec.getLong("total"));
        orderMap.put("aaData", orders);
        renderJson(orderMap);
    }
    
    // 更新InvoiceItem信息
    public void updateInvoiceItem(){
    	List<ArapCostInvoiceItemInvoiceNo> arapCostInvoiceItemInvoiceNos = new ArrayList<ArapCostInvoiceItemInvoiceNo>();
    	ArapCostInvoiceItemInvoiceNo itemInvoiceNo = ArapCostInvoiceItemInvoiceNo.dao.findById(getPara("invoiceItemId"));
    	String name = getPara("name");
    	String value = getPara("value");
    	if("amount".equals(name) && "".equals(value)){
    		value = "0";
    	}
    	itemInvoiceNo.set(name, value);
    	itemInvoiceNo.update();
    	if("invoice_no".equals(name) && !"".equals(value)){
    		arapCostInvoiceItemInvoiceNos = ArapCostInvoiceItemInvoiceNo.dao.find("select * from arap_cost_invoice_item_invoice_no where invoice_id = ?", getPara("costPreInvoiceOrderId"));    		
    	}
        renderJson(arapCostInvoiceItemInvoiceNos);
    }
    
    // 获取所有的发票号
    public void findAllInvoiceItemNo(){
    	List<ArapCostInvoiceItemInvoiceNo> arapCostInvoiceItemInvoiceNos = ArapCostInvoiceItemInvoiceNo.dao.find("select * from arap_cost_invoice_item_invoice_no where invoice_id = ?", getPara("costPreInvoiceOrderId"));
        renderJson(arapCostInvoiceItemInvoiceNos);
    }
    
    // 更新开票申请单
    public void updatePreInvoice(){
    	String name = getPara("name");
    	
    	String[] values = null;
    	Map<String,String[]> map = getParaMap();
    	for(Map.Entry<String,String[]> entry : map.entrySet()){
    		if("value[]".equals(entry.getKey())){
    			values = entry.getValue();
    		}
    	}
    	String costCheckOrderId = getPara("costCheckOrderId");
    	List<ArapCostOrderInvoiceNo> arapCostOrderInvoiceNos = ArapCostOrderInvoiceNo.dao.find("select * from arap_cost_order_invoice_no where cost_order_id = ?", costCheckOrderId);
    	for(ArapCostOrderInvoiceNo arapCostOrderInvoiceNo : arapCostOrderInvoiceNos){
    		arapCostOrderInvoiceNo.delete();
    	}
    	if(values != null){
	    	for(int i=0;i<values.length && values.length > 0;i++){
	    		ArapCostOrderInvoiceNo arapCostOrderInvoiceNo = ArapCostOrderInvoiceNo.dao.findFirst("select * from arap_cost_order_invoice_no where cost_order_id = ? and invoice_no = ?", costCheckOrderId, values[i]);
		    	if(arapCostOrderInvoiceNo == null){
		    		arapCostOrderInvoiceNo = new ArapCostOrderInvoiceNo();
		    		arapCostOrderInvoiceNo.set(name, values[i]);
		    		arapCostOrderInvoiceNo.set("cost_order_id", costCheckOrderId);
		    		arapCostOrderInvoiceNo.save();
		    	}
	    	}
    	}
        renderJson("{\"success\":true}");
    }
    
    public void costCheckOrderList(){
    	String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }
        String sp = getPara("sp");
        String customer = getPara("customer");
        String orderNo = getPara("orderNo");
        String beginTime = getPara("beginTime");
        String endTime = getPara("endTime");
        
        String sqlTotal = "";
        String sql = "select aco.*,group_concat(acoo.invoice_no separator ',') invoice_no,c.abbr cname,ul.user_name creator_name from arap_cost_order aco "
	        		+ " left join party p on p.id = aco.payee_id"
	        		+ " left join contact c on c.id = p.contact_id"
	        		+ " left join user_login ul on ul.id = aco.create_by"
	        		+ " left join arap_cost_order_invoice_no acoo on acoo.cost_order_id = aco.id"
	        		+ " where aco.status = '已确认' ";
        String condition = "";
        //TODO 客户条件过滤没有做
        if(sp != null || customer != null || orderNo != null
        		|| beginTime != null || endTime != null){
        	if (beginTime == null || "".equals(beginTime)) {
				beginTime = "1-1-1";
			}
			if (endTime == null || "".equals(endTime)) {
				endTime = "9999-12-31";
			}
			condition = " and ifnull(aco.order_no,'') like '%" + orderNo + "%' "
						+ " and ifnull(c.abbr,'') like '%" + sp + "%' "
						+ " and aco.create_stamp between '" + beginTime + "' and '" + endTime + "' ";
			
        }
        
        sqlTotal = "select count(1) total from arap_cost_order aco " 
        			+ " left join party p on p.id = aco.payee_id"
	        		+ " left join contact c on c.id = p.contact_id"
	        		+ " left join user_login ul on ul.id = aco.create_by"
	        		+ " left join arap_cost_order_invoice_no acoo on acoo.cost_order_id = aco.id"
	        		+ " where aco.status = '已确认' ";
        sql = sql + condition + "group by aco.id order by aco.create_stamp desc "+sLimit;

        Record rec = Db.findFirst(sqlTotal + condition);
        logger.debug("total records:" + rec.getLong("total"));
        
        List<Record> BillingOrders = Db.find(sql);

        Map BillingOrderListMap = new HashMap();
        BillingOrderListMap.put("sEcho", pageIndex);
        BillingOrderListMap.put("iTotalRecords", rec.getLong("total"));
        BillingOrderListMap.put("iTotalDisplayRecords", rec.getLong("total"));

        BillingOrderListMap.put("aaData", BillingOrders);

        renderJson(BillingOrderListMap);
    }
    
    public void costCheckOrderListById(){
    	String costPreInvoiceOrderId = getPara("costPreInvoiceOrderId");
    	if(costPreInvoiceOrderId == null || "".equals(costPreInvoiceOrderId)){
    		costPreInvoiceOrderId = "-1";
    	}
    	String sLimit = "";
    	String pageIndex = getPara("sEcho");
    	if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
    		sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
    	}
    	
    	String sqlTotal = "select count(aco.id) total from arap_cost_invoice_application_order appl_order left join arap_cost_order aco on aco.application_order_id = appl_order.id";
    	Record rec = Db.findFirst(sqlTotal);
    	logger.debug("total records:" + rec.getLong("total"));
    	
    	String sql = "select aco.*,c.abbr cname, (select group_concat(acai.invoice_no) from arap_cost_order aaia left join arap_cost_order_invoice_no acai on acai.cost_order_id = aaia.id where aaia.id = aco.id) invoice_no,"
    			+ " (select group_concat(cost_invoice_no.invoice_no separator ',') from arap_cost_invoice_item_invoice_no cost_invoice_no where cost_invoice_no.invoice_id = appl_order.id) all_invoice_no,ul.user_name creator_name"
    			+ " from arap_cost_invoice_application_order appl_order"
				+ " left join arap_cost_order aco on aco.application_order_id = appl_order.id"
				+ " left join party p on p.id = aco.payee_id left join contact c on c.id = p.contact_id"
        		+ " left join user_login ul on ul.id = aco.create_by"
				+ " where appl_order.id = "+costPreInvoiceOrderId
				+ " order by aco.create_stamp desc " + sLimit;
    	
    	logger.debug("sql:" + sql);
    	List<Record> BillingOrders = Db.find(sql);
    	
    	Map BillingOrderListMap = new HashMap();
    	BillingOrderListMap.put("sEcho", pageIndex);
    	BillingOrderListMap.put("iTotalRecords", rec.getLong("total"));
    	BillingOrderListMap.put("iTotalDisplayRecords", rec.getLong("total"));
    	
    	BillingOrderListMap.put("aaData", BillingOrders);
    	
    	renderJson(BillingOrderListMap);
    }

	public void searchAllAccount(){
		List<Account> accounts = Account.dao.find("select * from fin_account where type != 'REC'");
		renderJson(accounts);
	}
}
