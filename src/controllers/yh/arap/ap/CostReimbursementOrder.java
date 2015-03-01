package controllers.yh.arap.ap;

import interceptor.SetAttrLoginUserInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.DeliveryOrderMilestone;
import models.DepartOrderFinItem;
import models.Fin_item;
import models.UserLogin;
import models.yh.arap.ReimbursementOrder;
import models.yh.arap.ReimbursementOrderFinItem;

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
public class CostReimbursementOrder extends Controller {

	private Logger logger = Logger.getLogger(CostCheckOrderController.class);
	Subject currentUser = SecurityUtils.getSubject();
	 @RequiresPermissions(value = {PermissionConstant.PERMSSION_COSTREIMBURSEMENT_LIST})
	public void index() {
		render("/yh/arap/CostReimbursement/CostReimbursementList.html");
	}
	 @RequiresPermissions(value = {PermissionConstant.PERMSSION_COSTREIMBURSEMENT_CREATE, PermissionConstant.PERMSSION_COSTREIMBURSEMENT_UPDATE}, logical=Logical.OR)
	public void create() {
		List<Record> paymentItemList  = Db.find("select * from fin_item where type='报销'");
        setAttr("paymentItemList", paymentItemList);
        List<Record> attributionItemList  = Db.find("select * from fin_item where type='报销分类'");
        setAttr("attributionItemList", attributionItemList);
		render("/yh/arap/CostReimbursement/CostReimbursementEdit.html");
	}
	@RequiresPermissions(value = {PermissionConstant.PERMSSION_COSTREIMBURSEMENT_CREATE, PermissionConstant.PERMSSION_COSTREIMBURSEMENT_UPDATE}, logical=Logical.OR)
	public void saveReimbursementOrder() {
		String id = getPara("reimbursementId");
		//String status = getPara("status");
		String accountName = getPara("account_name");
		String accountNo = getPara("account_no");
		/*
		String amount = getPara("amount");
		String createId = getPara("create_id");
		String createStamp = getPara("create_stamp");
		String auditId = getPara("audit_id");
		String auditStamp = getPara("audit_stamp");
		String approvalId = getPara("approval_id");
		String approvalStamp = getPara("approval_stamp");
		*/
		String remark = getPara("remark");
		String invoicePayment = getPara("invoice_payment");
		String payment_type = getPara("payment_type");
		String orderNo = null;
		ReimbursementOrder rei = null;
		if (id == null || "".equals(id)) {
			// 单号
			orderNo = OrderNoGenerator.getNextOrderNo("YFBX");
			
			String name = (String) currentUser.getPrincipal();
			UserLogin users = UserLogin.dao
					.findFirst("select * from user_login where user_name='" + name + "'");
			
			rei = new ReimbursementOrder();
			rei.set("order_no", orderNo).set("status", "新建")
					.set("account_name", accountName).set("account_no", accountNo)
					.set("create_id", users.get("id"))
					.set("create_stamp", new Date()).set("remark", remark)
					.set("invoice_payment", invoicePayment).set("payment_type", payment_type)
					.save();
			
			DeliveryOrderMilestone milestone = new DeliveryOrderMilestone();
			milestone.set("status", "新建").set("create_by", users.get("id"))
					.set("create_stamp", new Date()).set("reimbursement_id", rei.get("id"))
					.save();
			
		} else {

			rei = ReimbursementOrder.dao.findById(id);
			rei.set("account_name", accountName).set("account_no", accountNo)
				.set("invoice_payment", invoicePayment).set("payment_type", payment_type)
				.set("remark", remark).update();
		}
		
		renderJson(rei);
	}
	 @RequiresPermissions(value = {PermissionConstant.PERMSSION_COSTREIMBURSEMENT_LIST})
	public void reimbursementList(){
		String orderNo = getPara("orderNo");
		String status = getPara("status");
		String auditName = getPara("auditName");
		
		String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }
        String sqlTotal = "";
        String sql = "";
        if(orderNo == null && status == null && auditName == null){
	        sqlTotal = "select count(1) total from reimbursement_order";
	    	 
	        sql = "select ro.*,(select sum(revocation_amount) from reimbursement_order_fin_item where order_id = ro.id) amount,"
	        		+ " (select user_name from user_login where id = ro.create_id) createName,"
	        		+ " (select user_name from user_login where id = ro.audit_id) auditName,"
	        		+ " (select user_name from user_login where id = ro.approval_id)  approvalName"
	        		+ " from reimbursement_order ro left join reimbursement_order_fin_item rofi on rofi.order_id = ro.id "
	        		+ " group by ro.id order by ro.create_stamp desc " + sLimit;
        }else{
        	sqlTotal = "select count(1) total from reimbursement_order ro left join reimbursement_order_fin_item rofi on rofi.order_id = ro.id "
        			+ " left join user_login u on u.id  = ro.audit_id "
	        		+ " where ro.order_no like '%" + orderNo + "%'"
	        		+ " and ro.status like '%" + status + "%'"
	        		+ " and ifnull(u.user_name,'') like '%" + auditName + "%'";
	    	 
	        sql = "select ro.*,(select sum(revocation_amount) from reimbursement_order_fin_item where order_id = ro.id) amount,"
	        		+ " (select user_name from user_login where id = ro.create_id) createName,"
	        		+ " (select user_name from user_login where id = ro.audit_id) auditName,"
	        		+ " (select user_name from user_login where id = ro.approval_id)  approvalName"
	        		+ " from reimbursement_order ro left join reimbursement_order_fin_item rofi on rofi.order_id = ro.id "
	        		+ " left join user_login u on u.id  = ro.audit_id "
	        		+ " where ro.order_no like '%" + orderNo + "%'"
	        		+ " and ro.status like '%" + status + "%'"
	        		+ " and ifnull(u.user_name,'') like '%" + auditName + "%'"
	        		+ " group by ro.id order by ro.create_stamp desc " + sLimit;
        }
        
		Record rec = Db.findFirst(sqlTotal);
		List<Record> orders = Db.find(sql);
        
        HashMap orderMap = new HashMap();
        orderMap.put("sEcho", pageIndex);
        orderMap.put("iTotalRecords", rec.getLong("total"));
        orderMap.put("iTotalDisplayRecords", rec.getLong("total"));
        orderMap.put("aaData", orders);
        renderJson(orderMap);
	}
	
	public void edit(){
		String id = getPara("id");
		ReimbursementOrder rei = ReimbursementOrder.dao.findById(id);
		setAttr("rei", rei);
		//创建人
		UserLogin create = UserLogin.dao
				.findFirst("select * from user_login where id='" + rei.get("create_id") + "'");
		setAttr("createName", create.get("user_name"));
		//审核人
		if(rei.get("audit_id") != null && !"".equals(rei.get("audit_id"))){
			UserLogin audit = UserLogin.dao
					.findFirst("select * from user_login where id='" + rei.get("audit_id") + "'");
			setAttr("auditName", audit.get("user_name"));
		}
		//审批人
		if(rei.get("approval_id") != null && !"".equals(rei.get("approval_id"))){
			UserLogin approval = UserLogin.dao
					.findFirst("select * from user_login where id='" + rei.get("approval_id") + "'");
			setAttr("approvalName", approval.get("user_name"));
		}
		
		List<Record> paymentItemList  = Db.find("select * from fin_item where type='报销'");
        setAttr("paymentItemList", paymentItemList);
        List<Record> attributionItemList  = Db.find("select * from fin_item where type='报销分类'");
        setAttr("attributionItemList", attributionItemList);
		
		render("/yh/arap/CostReimbursement/CostReimbursementEdit.html");
	}
	
	public void findUser(){
		String userId = getPara("userId");
		UserLogin approval = UserLogin.dao
				.findFirst("select * from user_login where id='" + userId + "'");
		renderJson(approval);
	}
	 @RequiresPermissions(value = {PermissionConstant.PERMSSION_COSTREIMBURSEMENT_CONFIRM})
	public void updateReimbursement(){
		String reimbursementId = getPara("reimbursementId");
		String btntTxt = getPara("btntTxt");
		String name = (String) currentUser.getPrincipal();
		UserLogin users = UserLogin.dao
				.findFirst("select * from user_login where user_name='" + name + "'");
		ReimbursementOrder rei = ReimbursementOrder.dao.findById(reimbursementId);
		DeliveryOrderMilestone milestone = new DeliveryOrderMilestone();
		milestone.set("create_by", users.get("id")).set("create_stamp", new Date()).set("reimbursement_id", rei.get("id"));
		if("审核".equals(btntTxt)){
			rei.set("status", "已审核").set("audit_id", users.get("id"))
			.set("audit_stamp", new Date()).update();
			milestone.set("status", "已审核").save();
		}else if("审批".equals(btntTxt)){
			rei.set("status", "已审批").set("approval_id", users.get("id"))
			.set("approval_stamp", new Date()).update();
			milestone.set("status", "已审批").save();
		}else if("取消审核".equals(btntTxt)){
			rei.set("status", "取消审核").set("audit_id", null)
			.set("audit_stamp", null).update();
			milestone.set("status", "取消审核").save();
		}else if("取消审批".equals(btntTxt)){
			rei.set("status", "取消审批").set("approval_id", null)
			.set("approval_stamp", null).update();
			milestone.set("status", "取消审批").save();
		}
		renderJson(rei);
	} 
	
	// 应付list
    public void accountPayable() {
        String id = getPara();
        if (id == null || id.equals("")) {
            Map orderMap = new HashMap();
            orderMap.put("sEcho", 0);
            orderMap.put("iTotalRecords", 0);
            orderMap.put("iTotalDisplayRecords", 0);
            orderMap.put("aaData", null);
            renderJson(orderMap);
            return;
        }
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }

        // 获取总条数
        String totalWhere = "";
        String sql = "select count(0) total from reimbursement_order_fin_item ";
        Record rec = Db.findFirst(sql + totalWhere);
        logger.debug("total records:" + rec.getLong("total"));

        // 获取当前页的数据
        List<Record> orders = Db.find("select d.*, f1.name item,f2.name attribution from reimbursement_order_fin_item d "
        		+ " left join fin_item f1 on d.fin_item_id = f1.id"
        		+ " left join fin_item f2 on d.fin_attribution_id  = f2.id"
                + " where d.order_id =" + id);

        Map orderMap = new HashMap();
        orderMap.put("sEcho", pageIndex);
        orderMap.put("iTotalRecords", rec.getLong("total"));
        orderMap.put("iTotalDisplayRecords", rec.getLong("total"));
        orderMap.put("aaData", orders);
        renderJson(orderMap);
    }
	//新增应付
    public void addNewRow() {
        List<Fin_item> items = new ArrayList<Fin_item>();
        String orderId = getPara();
        Fin_item item = Fin_item.dao.findFirst("select * from fin_item where type = '报销' order by id asc");
        Fin_item attribution = Fin_item.dao.findFirst("select * from fin_item where type = '报销分类' order by id asc");
        if(item != null){
        	ReimbursementOrderFinItem dFinItem = new ReimbursementOrderFinItem();
	        dFinItem.set("fin_item_id", item.get("id"))
	        .set("fin_attribution_id", attribution.get("id"))
	        .set("order_id", orderId)
	        .save();
        }
        items.add(item);
        renderJson(items);
    }
    //修改应付
    public void updateReimbursementOrderFinItem(){
    	String paymentId = getPara("paymentId");
    	String name = getPara("name");
    	String value = getPara("value");
    	ReimbursementOrder rei = null;
    	if("revocation_amount".equals(name) && "".equals(value)){
    		value = "0";
    	}
    	if("invoice_amount ".equals(name) && "".equals(value)){
    		value = "0";
    	}
    	if(paymentId != null && !"".equals(paymentId)){
    		ReimbursementOrderFinItem reimbursementOrderFinItem = ReimbursementOrderFinItem.dao.findById(paymentId);
    		reimbursementOrderFinItem.set(name, value);
    		reimbursementOrderFinItem.update();
    		rei = ReimbursementOrder.dao.findById(reimbursementOrderFinItem.get("order_id"));
    		if("revocation_amount".equals(name) && !"0".equals(value)){
    			Record rec = Db.findFirst("select sum(revocation_amount) amount from reimbursement_order_fin_item where order_id = "+ reimbursementOrderFinItem.get("order_id"));
    			rei.set("amount", rec.getDouble("amount")).update();
    		} 
    	}
        renderJson(rei);
    }
    // 删除应付 
    public void finItemdel() {
        String id = getPara();
        ReimbursementOrderFinItem.dao.deleteById(id);
        renderJson("{\"success\":true}");
    }
    
    // 应付list
    public void findAllMilestone() {
        String id = getPara();
        if (id == null || id.equals("")) {
            Map orderMap = new HashMap();
            orderMap.put("sEcho", 0);
            orderMap.put("iTotalRecords", 0);
            orderMap.put("iTotalDisplayRecords", 0);
            orderMap.put("aaData", null);
            renderJson(orderMap);
            return;
        }
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }

        // 获取总条数
        String totalWhere = "";
        String sql = "select count(0) total from delivery_order_milestone where reimbursement_id = " + id;
        Record rec = Db.findFirst(sql + totalWhere);
        logger.debug("total records:" + rec.getLong("total"));

        // 获取当前页的数据
        List<Record> orders = Db.find("select d.*,u.user_name,u.c_name from delivery_order_milestone d left join user_login u on u.id = d.create_by where d.reimbursement_id = " + id);

        Map orderMap = new HashMap();
        orderMap.put("sEcho", pageIndex);
        orderMap.put("iTotalRecords", rec.getLong("total"));
        orderMap.put("iTotalDisplayRecords", rec.getLong("total"));
        orderMap.put("aaData", orders);
        renderJson(orderMap);
    }
}
