package controllers.yh.arap.ap;

import interceptor.SetAttrLoginUserInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.DepartOrder;
import models.InsuranceOrder;
import models.Party;
import models.yh.delivery.DeliveryOrder;
import models.yh.profile.Contact;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import controllers.yh.util.PermissionConstant;

@RequiresAuthentication
@Before(SetAttrLoginUserInterceptor.class)
public class CostItemConfirmController extends Controller {
    private Logger logger = Logger.getLogger(CostItemConfirmController.class);
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CTC_AFFIRM})
    public void index() {
    	    render("/yh/arap/CostItemConfirm/CostItemConfirmList.html");
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
        render("/yh/arap/CostAcceptOrder/CostCheckOrderEdit.html");
    }

    // 应付条目列表
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CTC_AFFIRM})
    public void list() {
        String sLimit = "";
        String pageIndex = getPara("sEcho");
        if (getPara("iDisplayStart") != null && getPara("iDisplayLength") != null) {
            sLimit = " LIMIT " + getPara("iDisplayStart") + ", " + getPara("iDisplayLength");
        }
        
        String orderNo = getPara("orderNo");
        String sp = getPara("sp");
        String no = getPara("no");
        String beginTime = getPara("beginTime");
        String endTime = getPara("endTime");
        String status = getPara("status");
        String type = getPara("type");
        
        
        String sqlTotal = "";
       /* String sql = "select * from (select distinct dor.id,"
		        		+ "dor.order_no order_no,"
		        		+ "dor.status,"
		        		+ "c.abbr spname,"
		        		+ "toi.amount,"
		        		+ "ifnull(prod.volume,toi.volume)*toi.amount volume,"
		        		+ "ifnull(prod.weight,toi.weight)*toi.amount weight,"
		        		+ "dor.create_stamp create_stamp,ul.user_name creator,'配送' business_type, (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.type = '应付') pay_amount, group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = doi.transfer_order_id group by tor.id) separator '\r\n') transfer_order_no,dor.sign_status return_order_collection,dor.remark,oe.office_name office_name "
						+ " from delivery_order dor"
						+ " left join party p on p.id = dor.sp_id "
						+ " left join contact c on c.id = p.contact_id "
						+ " left join delivery_order_item doi on doi.delivery_id = dor.id "
						+ " left join transfer_order_item_detail toid on toid.id = doi.transfer_item_detail_id "
						+ " left join transfer_order_item toi on toi.id = toid.item_id "
						+ " left join product prod on toi.product_id = prod.id "
						+ " left join user_login ul on ul.id = dor.create_by "
						+ " left join warehouse w on w.id = dor.from_warehouse_id "
						+ " left join office oe on oe.id = w.office_id where dor.audit_status='新建' group by dor.id "
						+ " union"
						+ " select distinct dpr.id,"
						+ "dpr.depart_no order_no,"
						+ "dpr.status,"
						+ "c.abbr spname,"
						+ "(select sum(toi.amount) from depart_transfer dt left join transfer_order_item toi on toi.order_id = dt.order_id where depart_id = dpr.id) as amount,"
						+ "(select sum(toi.amount * ifnull(prod.volume,toi.volume)) from depart_transfer dt left join transfer_order_item toi on toi.order_id = dt.order_id where depart_id = dpr.id) volume,"
						+ "(select sum(toi.amount * ifnull(prod.weight,toi.weight)) from depart_transfer dt left join transfer_order_item toi on toi.order_id = dt.order_id where depart_id = dpr.id) weight,"
						+ "dpr.create_stamp create_stamp,"
						+ "ul.user_name creator,'零担' business_type, (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.depart_order_id = dpr.id and fi.type = '应付') pay_amount, group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = dtr.order_id) separator '\r\n') transfer_order_no,dpr.sign_status return_order_collection,dpr.remark,oe.office_name office_name "
						+ " from depart_order dpr "
						+ " left join depart_transfer dtr on dtr.depart_id = dpr.id"
						+ " left join transfer_order tor on tor.id = dtr.order_id "
						+ " left join transfer_order_item toi on toi.order_id = tor.id "
						+ " left join transfer_order_item_detail toid on toid.order_id = tor.id and toid.item_id = toi.id "
						+ " left join product prod on toi.product_id = prod.id "
						+ " left join user_login ul on ul.id = dpr.create_by left join party p on p.id = dpr.sp_id left join contact c on c.id = p.contact_id "
						+ " left join office oe on oe.id = tor.office_id where (ifnull(dtr.depart_id, 0) > 0) and dpr.audit_status='新建' group by dpr.id"
						+ " union "
						+ " select distinct dpr.id,"
						+ "dpr.depart_no order_no,"
						+ "dpr.status,c.abbr spname,"
						+ "toi.amount,"
						+ "ifnull(prod.volume,toi.volume)*toi.amount volume,"
						+ "ifnull(prod.weight,toi.weight)*toi.amount weight,"
						+ "dpr.create_stamp create_stamp,ul.user_name creator,"
						+ "'提货' business_type, (select sum(amount) from pickup_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.pickup_order_id = dpr.id and fi.type = '应付') pay_amount, group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = dtr.order_id) separator '\r\n') transfer_order_no,dpr.sign_status return_order_collection,dpr.remark,oe.office_name office_name "
						+ " from depart_order dpr "
						+ " left join depart_transfer dtr on dtr.pickup_id = dpr.id"
						+ " left join transfer_order tor on tor.id = dtr.order_id "
						+ " left join transfer_order_item toi on toi.order_id = tor.id "
						+ " left join transfer_order_item_detail toid on toid.order_id = tor.id and toid.item_id = toi.id "
						+ " left join product prod on toi.product_id = prod.id "
						+ " left join user_login ul on ul.id = dpr.create_by left join party p on p.id = dpr.sp_id left join contact c on c.id = p.contact_id "
						+ " left join office oe on oe.id = tor.office_id where (ifnull(dtr.pickup_id, 0) > 0) and dpr.audit_status='新建' and c.abbr is not null  group by dpr.id"
						+ " union "
						+ " select distinct ior.id,"
						+ "ior.order_no order_no,"
						+ "ior.status,"
						+ "'保险公司' spname,"
						+ "sum(toi.amount) amount,"
						+ "sum(ifnull(prod.volume,toi.volume)*toi.amount) volume,"
						+ "sum(ifnull(prod.weight,toi.weight)*toi.amount) weight,"
						+ "ior.create_stamp create_stamp,"
						+ "ul.user_name creator,"
						+ "'保险' business_type, "
						+ "(select sum(insurance_amount) from insurance_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.insurance_order_id = ior.id and fi.type = '应付') pay_amount, group_concat(distinct tor.order_no separator '\r\n') transfer_order_no,ior.sign_status return_order_collection,ior.remark,oe.office_name office_name "
						+ " from insurance_order ior "
						+ " left join transfer_order tor on ior.id = tor.insurance_id "
						+ " left join transfer_order_item toi on toi.order_id = tor.id "
						+ " left join transfer_order_item_detail toid on toid.order_id = tor.id and toid.item_id = toi.id "
						+ " left join product prod on toi.product_id = prod.id "
						+ " left join user_login ul on ul.id = ior.create_by"
						+ " left join office oe on oe.id = tor.office_id where ior.audit_status='新建' group by ior.id  ) as A";*/
        String sql = "select * from (select distinct dor.id,"
        		+ " dor.order_no order_no,"
        		+ " dor.status,"
        		+ " c.abbr spname,"
        		+ " toi.amount,"
        		+ " round(ifnull(prod.volume,toi.volume)*toi.amount,2) volume,"
        		+ " round(ifnull(prod.weight,toi.weight)*toi.amount,2) weight,"
        		+ " dor.create_stamp create_stamp,"
        		+ " ul.user_name creator,"
        		+ " '配送' business_type, "
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.type = '应付') pay_amount,"
        		+ " group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = doi.transfer_order_id group by tor.id) separator '<br/>') transfer_order_no,"
        		+ " dor.sign_status return_order_collection,"
        		+ " dor.remark,"
        		+ " oe.office_name office_name,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='运输费') as transport_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='搬运费') as carry_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='上楼费') as climb_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='保险费') as insurance_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='提货费') as take_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='安装费') as anzhuang_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='仓储费') as cangchu_cost,"
        		+ " (select sum(amount) from delivery_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.order_id = dor.id and fi.name='其他费用') as other_cost"
				+ " from delivery_order dor"
				+ " left join party p on p.id = dor.sp_id "
				+ " left join contact c on c.id = p.contact_id "
				+ " left join delivery_order_item doi on doi.delivery_id = dor.id "
				+ " left join transfer_order_item_detail toid on toid.id = doi.transfer_item_detail_id "
				+ " left join transfer_order_item toi on toi.id = toid.item_id "
				+ " left join product prod on toi.product_id = prod.id "
				+ " left join user_login ul on ul.id = dor.create_by "
				+ " left join warehouse w on w.id = dor.from_warehouse_id "
				+ " left join office oe on oe.id = w.office_id where dor.audit_status='新建' group by dor.id "
				+ " union"
				+ " select distinct dpr.id,"
				+ "dpr.depart_no order_no,"
				+ "dpr.status,"
				+ "c.abbr spname,"
				+ "(select count(*) from transfer_order_item_detail where depart_id =dpr.id ) as amount,"
				+ "round((select count(id) * volume as volume from transfer_order_item_detail where depart_id =dpr.id),2) volume,"
				+ "round((select count(id) * weight as weight from transfer_order_item_detail where depart_id =dpr.id),2) weight,"
				+ "dpr.create_stamp create_stamp,"
				+ "ul.user_name creator,"
				+ "dpr.transfer_type business_type, "
				+ "(select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where dofi.depart_order_id = dpr.id and fi.type = '应付') pay_amount, "
				+ "group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = dtr.order_id) separator '\r\n') transfer_order_no,"
				+ "dpr.sign_status return_order_collection,"
				+ "dpr.remark,"
				+ "oe.office_name office_name,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='运输费') transport_cost, "
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='搬运费') carry_cost,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='上楼费') climb_cost,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='保险费') insurance_cost,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='提货费') take_cost, "
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='安装费') anzhuang_cost,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='仓储费') cangchu_cost,"
				+ " (select sum(amount) from depart_order_fin_item dofi left join fin_item fi on fi.id = dofi.fin_item_id where depart_order_id = dpr.id and fi.name='其他费用') other_cost"
				+ " from depart_order dpr "
				+ " left join depart_transfer dtr on dtr.depart_id = dpr.id"
				+ " left join transfer_order tor on tor.id = dtr.order_id "
				+ " left join transfer_order_item toi on toi.order_id = tor.id "
				+ "	left join depart_order_fin_item dofi on dofi.depart_order_id = dpr.id "
				+ " left join transfer_order_item_detail toid on toid.order_id = tor.id and toid.item_id = toi.id "
				+ " left join product prod on toi.product_id = prod.id "
				+ " left join user_login ul on ul.id = dpr.create_by left join party p on p.id = dpr.sp_id left join contact c on c.id = p.contact_id "
				+ " left join office oe on oe.id = tor.office_id where (ifnull(dtr.depart_id, 0) > 0) and dpr.audit_status='新建' and dofi.id is not null group by dpr.id"
				+ " union "
				+ " select distinct dpr.id,"
				+ " dpr.depart_no order_no,"
				+ " dpr.status,c.abbr spname,"
				+ " (select count(id) from transfer_order_item_detail where pickup_id = pofi.pickup_order_id)as amount,"
				+ " round((select sum(volume) as volume from transfer_order_item_detail where pickup_id = pofi.pickup_order_id),2) volume,"
				+ " round((select sum(weight) from transfer_order_item_detail where pickup_id = pofi.pickup_order_id),2) weight,"
				+ " dpr.create_stamp create_stamp,ul.user_name creator,"
				+ " '提货' business_type, "
				+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.type = '应付') pay_amount, "
				+ " group_concat(distinct (select tor.order_no from transfer_order tor where tor.id = dtr.order_id) separator '\r\n') transfer_order_no,"
				+ " dpr.sign_status return_order_collection,"
				+ " dpr.remark,"
				+ " oe.office_name office_name,"
				+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='运输费') as transport_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='搬运费') as carry_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='上楼费') as climb_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='保险费') as insurance_cost,"
				+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='提货费') as take_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='安装费') as anzhuang_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='仓储费') as cangchu_cost,"
        		+ " (select sum(amount) from pickup_order_fin_item pofi left join fin_item fi on fi.id = pofi.fin_item_id where pofi.pickup_order_id = dpr.id and fi.name='其他费用') as other_cost"
				+ " from depart_order dpr "
				+ " left join depart_transfer dtr on dtr.pickup_id = dpr.id"
				+ " left join transfer_order tor on tor.id = dtr.order_id "
				+ " left join transfer_order_item toi on toi.order_id = tor.id "
				+ " left join transfer_order_item_detail toid on toid.order_id = tor.id and toid.item_id = toi.id "
				+ " left join product prod on toi.product_id = prod.id "
				+ " left join pickup_order_fin_item pofi on pofi.pickup_order_id = dtr.pickup_id"
				+ " left join user_login ul on ul.id = dpr.create_by left join party p on p.id = dpr.sp_id left join contact c on c.id = p.contact_id "
				+ " left join office oe on oe.id = tor.office_id where (ifnull(dtr.pickup_id, 0) > 0) and dpr.audit_status='新建' and pofi.id is not null  group by dpr.id"
				+ " union "
				+ " select distinct ior.id,"
				+ " ior.order_no order_no,"
				+ " ior.status,"
				+ " '保险公司' spname,"
				+ " (select sum(amount) from transfer_order_item toi where toi.order_id in(select tro.id from transfer_order tro where tro.insurance_id  = ior.id )) amount,"
				+ " (select round(sum(ifnull(prod.volume,toi.volume)*amount),2) from transfer_order_item toi left join product prod on toi.product_id = prod.id  where toi.order_id in(select tro.id from transfer_order tro where tro.insurance_id  = ior.id )) volume,"
				+ " (select round(sum(ifnull(prod.weight,toi.weight)*amount),2) from transfer_order_item toi left join product prod on toi.product_id = prod.id  where toi.order_id in(select tro.id from transfer_order tro where tro.insurance_id  = ior.id )) weight,"
				+ " ior.create_stamp create_stamp,"
				+ " ul.user_name creator,"
				+ " '保险' business_type, "
				+ " (select sum(insurance_amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.type = '应付') pay_amount, "
				+ " group_concat(distinct tor.order_no separator '\r\n') transfer_order_no,"
				+ " ior.sign_status return_order_collection,"
				+ " ior.remark,"
				+ " oe.office_name office_name,"
				+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='运输费') as transport_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='搬运费') as carry_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='上楼费') as climb_cost,"
        		+ " (select sum(insurance_amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='保险费') as insurance_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='提货费') as take_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='安装费') as anzhuang_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='仓储费') as cangchu_cost,"
        		+ " (select sum(amount) from insurance_fin_item ifi left join fin_item fi on fi.id = ifi.fin_item_id where ifi.insurance_order_id = ior.id and fi.name='其他费用') as other_cost"
				+ " from insurance_order ior "
				+ " left join transfer_order tor on ior.id = tor.insurance_id "
				+ " left join transfer_order_item toi on toi.order_id = tor.id "
				+ " left join user_login ul on ul.id = ior.create_by"
				+ " left join office oe on oe.id = tor.office_id where ior.audit_status='新建' group by ior.id  ) as A ";
        String condition = "";
      
        if(orderNo != null || sp != null || no != null || beginTime != null
        	|| endTime !=null || status != null || type != null){
        	if (beginTime == null || "".equals(beginTime)) {
				beginTime = "1-1-1";
			}
			if (endTime == null || "".equals(endTime)) {
				endTime = "9999-12-31";
			}
			 
        	condition =  " where ifnull(order_no,'') like '%" + no + "%' "
        			+ " and ifnull(transfer_order_no,'') like '%" + orderNo + "%' "
        			+ " and ifnull(status,'') like '%" + status + "%' "
        			+ " and ifnull(spname,'') like '%" + sp + "%' "
        			+ " and create_stamp between '" + beginTime + "' and '" + endTime + "' "
        			+ " and ifnull(business_type,'') like '%" + type + "%'";
        }
       
        sqlTotal = " select count(1) total from (" + sql + condition + ") as B"; 
        Record rec = Db.findFirst(sqlTotal);
        logger.debug("total records:" + rec.getLong("total"));
       
        List<Record> BillingOrders = Db.find(sql + condition + " order by create_stamp desc" + sLimit);

        Map BillingOrderListMap = new HashMap();
        BillingOrderListMap.put("sEcho", pageIndex);
        BillingOrderListMap.put("iTotalRecords", rec.getLong("total"));
        BillingOrderListMap.put("iTotalDisplayRecords", rec.getLong("total"));

        BillingOrderListMap.put("aaData", BillingOrders);

        renderJson(BillingOrderListMap);
    }
    @RequiresPermissions(value = {PermissionConstant.PERMSSION_CTC_AFFIRM})
    public void costConfiremReturnOrder(){
    	String ids = getPara("ids");
    	String orderNos = getPara("orderNos");
    	String[] idArr = ids.split(",");
    	String[] orderNoArr = orderNos.split(",");
    	for(int i=0 ; i<idArr.length ; i++){
    		if("提货".equals(orderNoArr[i])){
    			DepartOrder pickupOrder = DepartOrder.dao.findById(idArr[i]);
    			pickupOrder.set("audit_status", "已确认");
    			pickupOrder.update();
    		}else if("零担".equals(orderNoArr[i]) || "整车".equals(orderNoArr[i])){
    			DepartOrder departOrder = DepartOrder.dao.findById(idArr[i]);
    			departOrder.set("audit_status", "已确认");
    			departOrder.update();
    		}else if("配送".equals(orderNoArr[i])){
    			DeliveryOrder deliveryOrder = DeliveryOrder.dao.findById(idArr[i]);
    			deliveryOrder.set("audit_status", "已确认");
    			deliveryOrder.update();
    		}else{
    			InsuranceOrder insuranceOrder = InsuranceOrder.dao.findById(idArr[i]);
    			insuranceOrder.set("audit_status", "已确认");
    			insuranceOrder.update();
    		}
    	}
        renderJson("{\"success\":true}");
    }
}
