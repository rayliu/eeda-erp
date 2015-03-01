package controllers.yh.arap.ar;

import interceptor.SetAttrLoginUserInterceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.ArapAccountAuditLog;
import models.ArapChargeInvoice;
import models.ArapChargeInvoiceApplication;
import models.ArapChargeInvoiceApplicationItem;
import models.ArapChargeOrder;
import models.ArapMiscChargeOrder;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import controllers.yh.LoginUserController;
import controllers.yh.util.PermissionConstant;

@RequiresAuthentication
@Before(SetAttrLoginUserInterceptor.class)
public class ChargeAcceptOrderController extends Controller {
    private Logger logger = Logger.getLogger(ChargeAcceptOrderController.class);
    Subject currentUser = SecurityUtils.getSubject();
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_COLLECTIONCONFIRM_LIST})
    public void index() {
    	setAttr("type", "CUSTOMER");
    	setAttr("classify", "receivable");
    	    render("/yh/arap/ChargeAcceptOrder/ChargeAcceptOrderList.html");
    }

    // billing order 列表
    public void list() {
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }

        String sqlTotal = "select count(1) total from (select aci.id, aci.order_no, aci.status, group_concat(invoice_item.invoice_no separator '\r\n') invoice_no, aci.create_stamp create_time, aci.remark "
        		+ "from arap_charge_invoice aci left join arap_charge_invoice_item_invoice_no invoice_item on aci.id = invoice_item.invoice_id group by aci.id "
				+ "union all "
				+ "select id, order_no, status, '' invoice_no, create_stamp create_time, remark  from arap_misc_charge_order where status='新建') tab";
        Record rec = Db.findFirst(sqlTotal);
        logger.debug("total records:" + rec.getLong("total"));

        /*String sql = "select aci.id, aci.order_no, aci.status, group_concat(invoice_item.invoice_no separator '\r\n') invoice_no, aci.create_stamp create_time, aci.remark,aci.total_amount total_amount,c.abbr cname "
        		+ " from arap_charge_invoice aci "
        		+ " left join party p on p.id = aci.payee_id left join contact c on c.id = p.contact_id"
        		+ " left join arap_charge_invoice_item_invoice_no invoice_item on aci.id = invoice_item.invoice_id group by aci.id "
				+ " union all "
				+ " select amco.id, amco.order_no, amco.status, '' invoice_no, amco.create_stamp create_time, amco.remark, amco.total_amount,c.abbr cname "
				+ " from arap_misc_charge_order amco"
				+ " left join party p on p.id = amco.payee_id left join contact c on c.id = p.contact_id"
				+ " where amco.status='新建' "
				+ " order by create_time desc " + sLimit;*/
        String status = "已审批";
        String status_filter = getPara("status");
        if(status_filter != null && !"".equals(status_filter)){
        	status = status_filter;
        }
        String sql = "select aci.id, aci.order_no, aci.status, group_concat(invoice_item.invoice_no separator '\r\n') invoice_no, aci.create_stamp create_time, aci.remark,aci.total_amount total_amount,c.abbr cname "
        		+ " from arap_charge_invoice aci "
        		+ " left join party p on p.id = aci.payee_id left join contact c on c.id = p.contact_id"
        		+ " left join arap_charge_invoice_item_invoice_no invoice_item on aci.id = invoice_item.invoice_id where aci.status = '" + status + "' ";
        
       
        List<Record> BillingOrders = Db.find(sql + " group by aci.id order by create_time desc " + sLimit);

        Map BillingOrderListMap = new HashMap();
        BillingOrderListMap.put("sEcho", pageIndex);
        BillingOrderListMap.put("iTotalRecords", rec.getLong("total"));
        BillingOrderListMap.put("iTotalDisplayRecords", rec.getLong("total"));

        BillingOrderListMap.put("aaData", BillingOrders);

        renderJson(BillingOrderListMap);
    }
    
    // 收款
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_COLLECTIONCONFIRM_CONFIRM})
    public void chargeAccept(){
    	ArapChargeOrder arapAuditOrder = ArapChargeOrder.dao.findById(getPara("chargeCheckOrderId"));
    	arapAuditOrder.set("status", "completed");
    	arapAuditOrder.update();
        renderJson("{\"success\":true}");
    }
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_COLLECTIONCONFIRM_CONFIRM})
    @Before(Tx.class)
    public void save(){
    	String chargeIds = getPara("chargeIds");
    	String paymentMethod = getPara("paymentMethod");
    	String accountId = getPara("accountTypeSelect");
    	String[] chargeIdArr = null; 
    	if(chargeIds != null && !"".equals(chargeIds)){
    		chargeIdArr = chargeIds.split(",");
    	}
    	for(int i=0;i<chargeIdArr.length;i++){
    		String[] arr = chargeIdArr[i].split(":");
    		String orderId = arr[0];
    		String orderNo = arr[1];
            if(orderNo.startsWith("SGSK")){
				ArapMiscChargeOrder arapMiscChargeOrder = ArapMiscChargeOrder.dao.findById(orderId);
				arapMiscChargeOrder.set("status", "已收款确认");
				arapMiscChargeOrder.update();
            }else{
                ArapChargeInvoice arapChargeInvoice = ArapChargeInvoice.dao.findById(orderId);
                arapChargeInvoice.set("status", "已收款确认");
                arapChargeInvoice.update();
                /*//收款确认后，改变应收开票申请和应收对账单以及手工收款单的状态
                List<ArapChargeInvoiceApplication> list = ArapChargeInvoiceApplication.dao.find("select * from arap_charge_invoice_application_order where invoice_order_id = ?",orderId);
                for (ArapChargeInvoiceApplication application : list) {
                	application.set("status", "已收款确认");
                	application.update();
					List<ArapChargeInvoiceApplicationItem> inList = ArapChargeInvoiceApplicationItem.dao.find("select * from arap_charge_invoice_application_item where invoice_application_id = ?",application.get("id"));
					for (ArapChargeInvoiceApplicationItem arapChargeInvoiceApplicationItem : inList) {
						ArapChargeOrder arapAuditOrder = ArapChargeOrder.dao.findById(arapChargeInvoiceApplicationItem.get("charge_order_id"));
						arapAuditOrder.set("status", "已收款确认");
						arapAuditOrder.update();
						List<ArapMiscChargeOrder> arapMiscChargeOrderList = ArapMiscChargeOrder.dao.find("select * from arap_misc_charge_order where charge_order_id = ?",arapAuditOrder.get("id"));
						if(arapMiscChargeOrderList.size()>0){
							for (ArapMiscChargeOrder arapMiscChargeOrder : arapMiscChargeOrderList) {
								arapMiscChargeOrder.set("status", "已收款确认");
								arapMiscChargeOrder.update();
							}
						}
					}
                }*/
                
            }
			
			//现金 或 银行  金额处理
			if("cash".equals(paymentMethod)){
				Account account = Account.dao.findFirst("select * from fin_account where bank_name ='现金'");
				if(account!=null){
					Record rec = null;
					if(orderNo.startsWith("SGSK")){
						rec = Db.findFirst("select sum(amcoi.amount) total from arap_misc_charge_order amco, arap_misc_charge_order_item amcoi "
								+ "where amco.id = amcoi.misc_order_id and amco.order_no='"+orderNo+"'");
						if(rec!=null){
							double total = rec.getDouble("total")==null?0.0:rec.getDouble("total");
							//现金账户 金额处理
							account.set("amount", (account.getDouble("amount")==null?0.0:account.getDouble("amount")) + total).update();
							//日记账
							createAuditLog(orderId, account, total, paymentMethod, "手工收款单");
						}
					}else{
						rec = Db.findFirst("select aci.total_amount total from arap_charge_invoice aci where aci.order_no='"+orderNo+"'");
						if(rec!=null){
							double total = rec.getDouble("total")==null?0.0:rec.getDouble("total");
							//现金账户 金额处理
							account.set("amount", (account.getDouble("amount")==null?0.0:account.getDouble("amount")) + total).update();
							//日记账
							createAuditLog(orderId, account, total, paymentMethod, "应收开票记录单");
						}
					}
				}
			}else{//银行账户  金额处理
			    Account account = Account.dao.findFirst("select * from fin_account where id ="+accountId);
                if(account!=null){
                	Record rec = null;
					if(orderNo.startsWith("SGSK")){
						rec = Db.findFirst("select sum(amcoi.amount) total from arap_misc_charge_order amco, arap_misc_charge_order_item amcoi "
								+ "where amco.id = amcoi.misc_order_id and amco.order_no='"+orderNo+"'");
	                    if(rec!=null){
	                        double total = rec.getDouble("total")==null?0.0:rec.getDouble("total");
	                        //银行账户 金额处理
	                        account.set("amount", (account.getDouble("amount")==null?0.0:account.getDouble("amount")) + total).update();
	                        //日记账
	                        createAuditLog(orderId, account, total, paymentMethod, "手工收款单");
	                    }
					}else{
						rec = Db.findFirst("select aci.total_amount total from arap_charge_invoice aci where aci.order_no='"+orderNo+"'");
	                    if(rec!=null){
	                        double total = rec.getDouble("total")==null?0.0:rec.getDouble("total");
	                        //银行账户 金额处理
	                        account.set("amount", (account.getDouble("amount")==null?0.0:account.getDouble("amount")) + total).update();
	                        //日记账
	                        createAuditLog(orderId, account, total, paymentMethod, "应收开票记录单");
	                    }
					}
                }
			}
    	}
    	redirect("/chargeAcceptOrder");
    }

    private void createAuditLog(String orderId, Account account, double total, String paymentMethod, String sourceOrder) {
        ArapAccountAuditLog auditLog = new ArapAccountAuditLog();
        auditLog.set("payment_method", paymentMethod);
        auditLog.set("payment_type", ArapAccountAuditLog.TYPE_CHARGE);
        auditLog.set("amount", total);
        auditLog.set("creator", LoginUserController.getLoginUserId(this));
        auditLog.set("create_date", new Date());
        auditLog.set("misc_order_id", orderId);
        auditLog.set("invoice_order_id", null);
        auditLog.set("account_id", account.get("id"));
        auditLog.set("source_order", sourceOrder);
        auditLog.save();
    }
}
