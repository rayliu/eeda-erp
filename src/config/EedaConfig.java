package config;

import handler.UrlHanlder;

import java.lang.management.ManagementFactory;
import java.sql.SQLException;

import models.Account;
import models.ArapAccountAuditLog;
import models.ArapChargeApplicationInvoiceNo;
import models.ArapChargeInvoice;
import models.ArapChargeInvoiceApplication;
import models.ArapChargeInvoiceApplicationItem;
import models.ArapChargeInvoiceItemInvoiceNo;
import models.ArapChargeItem;
import models.ArapChargeOrder;
import models.ArapCostInvoice;
import models.ArapCostInvoiceApplication;
import models.ArapCostInvoiceItemInvoiceNo;
import models.ArapCostItem;
import models.ArapCostOrder;
import models.ArapCostOrderInvoiceNo;
import models.ArapMiscChargeOrder;
import models.ArapMiscChargeOrderItem;
import models.Category;
import models.DeliveryOrderFinItem;
import models.DeliveryOrderItem;
import models.DeliveryOrderMilestone;
import models.DepartOrder;
import models.DepartOrderFinItem;
import models.DepartPickupOrder;
import models.DepartTransferOrder;
import models.Fin_item;
import models.InsuranceFinItem;
import models.InsuranceOrder;
import models.InventoryItem;
import models.Location;
import models.Office;
import models.OrderAttachmentFile;
import models.OrderStatus;
import models.Party;
import models.PartyAttribute;
import models.Permission;
import models.PickupOrderFinItem;
import models.Product;
import models.ReturnOrder;
import models.Role;
import models.RolePermission;
import models.Toll;
import models.TransferOrder;
import models.TransferOrderFinItem;
import models.TransferOrderItem;
import models.TransferOrderItemDetail;
import models.TransferOrderMilestone;
import models.UserCustomer;
import models.UserLogin;
import models.UserOffice;
import models.UserRole;
import models.Warehouse;
import models.WarehouseOrder;
import models.WarehouseOrderItem;
import models.eeda.Case;
import models.eeda.Leads;
import models.eeda.Order;
import models.eeda.OrderItem;
import models.eeda.ServiceProvider;
import models.yh.arap.ArapMiscCostOrder;
import models.yh.arap.BillingOrder;
import models.yh.arap.BillingOrderItem;
import models.yh.arap.ReimbursementOrder;
import models.yh.arap.ReimbursementOrderFinItem;
import models.yh.carmanage.CarSummaryDetail;
import models.yh.carmanage.CarSummaryDetailOilFee;
import models.yh.carmanage.CarSummaryDetailOtherFee;
import models.yh.carmanage.CarSummaryDetailRouteFee;
import models.yh.carmanage.CarSummaryDetailSalary;
import models.yh.carmanage.CarSummaryOrder;
import models.yh.contract.Contract;
import models.yh.contract.ContractItem;
import models.yh.delivery.DeliveryOrder;
import models.yh.delivery.DeliveryPlanOrder;
import models.yh.delivery.DeliveryPlanOrderCarinfo;
import models.yh.delivery.DeliveryPlanOrderDetail;
import models.yh.delivery.DeliveryPlanOrderMilestone;
import models.yh.profile.Carinfo;
import models.yh.profile.Contact;
import models.yh.profile.CustomizeField;
import models.yh.profile.OfficeCofig;
import models.yh.profile.Route;
import models.yh.returnOrder.ReturnOrderFinItem;

import org.apache.log4j.Logger;
import org.bee.tl.ext.jfinal.BeetlRenderFactory;
import org.h2.tools.Server;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.weixin.demo.WeixinApiController;
import com.jfinal.weixin.demo.WeixinMsgController;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

public class EedaConfig extends JFinalConfig {
    private Logger logger = Logger.getLogger(EedaConfig.class);

    private static final String H2 = "H2";
    private static final String Mysql = "Mysql";
    private static final String ProdMysql = "ProdMysql";
      
    public static String mailUser;
    public static String mailPwd;
    
    /**
     * 
     * 供Shiro插件使用 。
     */
    Routes routes;

    C3p0Plugin cp;
    ActiveRecordPlugin arp;

    @Override
	public void configConstant(Constants me) {
        //加载配置文件    	
        loadPropertyFile("app_config.txt");
        getPropertyToBoolean("devMode", false);
        
    	// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
    	ApiConfigKit.setDevMode(me.getDevMode());
        
    	me.setDevMode(me.getDevMode());

        BeetlRenderFactory templateFactory = new BeetlRenderFactory();
        me.setMainRenderFactory(templateFactory);

        BeetlRenderFactory.groupTemplate.setCharset("utf-8");// 没有这句，html上的汉字会乱码

        // 注册后，可以使beetl html中使用shiro tag
        BeetlRenderFactory.groupTemplate.registerFunctionPackage("shiro", new ShiroExt());

        //没有权限时跳转到login
        me.setErrorView(401, "/yh/noLogin.html");//401 authenticate err
        me.setErrorView(403, "/yh/noPermission.html");// authorization err
        
        //内部出错跳转到对应的提示页面，需要考虑提供更详细的信息。
        me.setError404View("/yh/err404.html");
        me.setError500View("/yh/err500.html");
        
        // me.setErrorView(503, "/login.html");
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        // get pid
        String pid = name.split("@")[0];
        logger.info("Pid is: " + pid);
    }

    @Override
	public void configRoute(Routes me) {
        this.routes = me;

        //TODO: 为之后去掉 yh做准备
        String contentPath="/";//"yh";
        
        // eeda project controller
        setErpRoute(me);

        // me.add("/fileUpload", HelloController.class);
        //setYhRoute(me, contentPath);
        
    }

	private void setErpRoute(Routes me) {
		me.add("/", controllers.eeda.AppController.class);
        me.add("/case", controllers.eeda.CaseController.class);
        me.add("/user", controllers.eeda.UserProfileController.class);
        me.add("/salesOrder", controllers.eeda.SalesOrderController.class);
        me.add("/loan", controllers.eeda.LoanController.class);
        me.add("/propertyClient", controllers.eeda.PropertyClientController.class);
        me.add("/sp", controllers.eeda.ServiceProviderController.class);
        //me.add("/au", AdminUserController.class);
	}

	private void setYhRoute(Routes me, String contentPath) {
		// yh project controller
        me.add("/", controllers.yh.AppController.class, contentPath);
        me.add("/debug", controllers.yh.LogController.class, contentPath);
        
        me.add("/loginUser", controllers.yh.LoginUserController.class, contentPath);
        //register loginUser
        me.add("/register",controllers.yh.RegisterUserController.class,contentPath);
        me.add("/reset",controllers.yh.ResetPassWordController.class,contentPath);
        
        me.add("/role", controllers.yh.RoleController.class, contentPath);
        me.add("/userRole",controllers.yh.UserRoleController.class,contentPath);
        me.add("/toll", controllers.yh.TollController.class, contentPath);
        me.add("/privilege", controllers.yh.PrivilegeController.class, contentPath);
        me.add("/pay", controllers.yh.PayController.class, contentPath);
        me.add("/ownCarPay", controllers.yh.PayController.class, contentPath);
        me.add("/customer", controllers.yh.profile.CustomerController.class, contentPath);
        me.add("/serviceProvider", controllers.yh.profile.ServiceProviderController.class, contentPath);
        me.add("/location", controllers.yh.LocationController.class, contentPath);
        me.add("/customerContract", controllers.yh.contract.ContractController.class, contentPath);
        me.add("/spContract", controllers.yh.contract.ContractController.class, contentPath);
        me.add("/route", controllers.yh.RouteController.class, contentPath);
        me.add("/office", controllers.yh.OfficeController.class, contentPath);
        me.add("/product", controllers.yh.profile.ProductController.class, contentPath);
        me.add("/warehouse", controllers.yh.profile.WarehouseController.class, contentPath);
        me.add("/orderStatus", controllers.yh.profile.OrderStatusController.class, contentPath);
        me.add("/account", controllers.yh.AccountController.class, contentPath);
        me.add("/transferOrder", controllers.yh.order.TransferOrderController.class);
        me.add("/transferOrderItem", controllers.yh.order.TransferOrderItemController.class, contentPath);
        me.add("/transferOrderItemDetail", controllers.yh.order.TransferOrderItemDetailController.class, contentPath);
        me.add("/transferOrderMilestone", controllers.yh.order.TransferOrderMilestoneController.class, contentPath);
        me.add("/returnOrder", controllers.yh.returnOrder.ReturnOrderController.class, contentPath);
        me.add("/delivery", controllers.yh.delivery.DeliveryController.class, contentPath);

        me.add("/deliverySpContract", controllers.yh.contract.ContractController.class, contentPath);

        me.add("/deliveryOrderMilestone", controllers.yh.delivery.DeliveryOrderMilestoneController.class, contentPath);
        me.add("/pickupOrder", controllers.yh.pickup.PickupOrderController.class, contentPath);

        me.add("/paymentCheckOrder", controllers.yh.arap.PaymentCheckOrderController.class, contentPath);

        me.add("/copeCheckOrder", controllers.yh.arap.CopeCheckOrderController.class, contentPath);
        me.add("/departOrder", controllers.yh.departOrder.DepartOrderController.class, contentPath);
        me.add("/gateIn", controllers.yh.inventory.InventoryController.class, contentPath);
        me.add("/gateOut", controllers.yh.inventory.InventoryController.class, contentPath);
        me.add("/stock", controllers.yh.inventory.InventoryController.class, contentPath);
        me.add("/carinfo", controllers.yh.profile.CarinfoController.class, contentPath);
        me.add("/carmanage", controllers.yh.profile.CarinfoController.class, contentPath);
        me.add("/carsummary", controllers.yh.carmanage.CarSummaryController.class, contentPath);
        me.add("/carreimbursement", controllers.yh.carmanage.CarReimbursementController.class, contentPath);
        me.add("/driverinfo", controllers.yh.profile.CarinfoController.class, contentPath);
        me.add("/spdriverinfo", controllers.yh.profile.CarinfoController.class, contentPath);
        me.add("/spcarinfo", controllers.yh.profile.CarinfoController.class, contentPath);
        me.add("/deliveryMilestone", controllers.yh.delivery.DeliveryController.class, contentPath);
        //配送调车单
        me.add("/deliveryPlanOrder", controllers.yh.delivery.DeliveryPlanOrderController.class, contentPath);
        
        //ar= account revenue  应收条目处理
        me.add("/chargeConfiremList", controllers.yh.arap.ar.ChargeItemConfirmController.class, contentPath);
        me.add("/chargeCheckOrder", controllers.yh.arap.ar.ChargeCheckOrderController.class, contentPath);
        me.add("/chargePreInvoiceOrder", controllers.yh.arap.ar.ChargePreInvoiceOrderController.class, contentPath);
        me.add("/chargeInvoiceOrder", controllers.yh.arap.ar.ChargeInvoiceOrderController.class, contentPath);
        me.add("/chargeAdjustOrder", controllers.yh.arap.ar.ChargeAdjustOrderController.class, contentPath);
        me.add("/chargeMiscOrder", controllers.yh.arap.ar.ChargeMiscOrderController.class, contentPath);
        me.add("/chargeAcceptOrder", controllers.yh.arap.ar.ChargeAcceptOrderController.class, contentPath);
        //ap 应付条目处理
        me.add("/costConfirmList", controllers.yh.arap.ap.CostItemConfirmController.class, contentPath);
        me.add("/costCheckOrder", controllers.yh.arap.ap.CostCheckOrderController.class, contentPath);
        me.add("/costPreInvoiceOrder", controllers.yh.arap.ap.CostPreInvoiceOrderController.class, contentPath);
        me.add("/costAdjustOrder", controllers.yh.arap.ap.CostAdjustOrderController.class, contentPath);
        me.add("/costAcceptOrder", controllers.yh.arap.ap.CostAcceptOrderController.class, contentPath);
        //应付报销单
        me.add("/costReimbursement", controllers.yh.arap.ap.CostReimbursementOrder.class, contentPath);
        
        me.add("/costMiscOrder", controllers.yh.arap.ap.CostMiscOrderController.class, contentPath);
        
        //audit log
        me.add("/accountAuditLog", controllers.yh.arap.AccountAuditLogController.class, contentPath);
        //insuranceOrder
        me.add("/insuranceOrder", controllers.yh.insurance.InsuranceOrderController.class, contentPath);
        me.add("/report", controllers.yh.report.ReportController.class, contentPath);
        me.add("/statusReport", controllers.yh.statusReport.StatusReportColler.class, contentPath);
        
        me.add("/doc", controllers.yh.doc.DocController.class, contentPath);
        //微信页面路由
        me.add("/wx", controllers.yh.wx.WxController.class, contentPath);
        //微信API路由
        me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class, "/api");
	}

    @Override
	public void configPlugin(Plugins me) {
        // 加载Shiro插件, for backend notation, not for UI
    	me.add(new ShiroPlugin(routes));
    	
        mailUser = getProperty("mail_user_name");
        mailPwd = getProperty("mail_pwd");
        // H2 or mysql
        initDBconnector();

        me.add(cp);

        arp = new ActiveRecordPlugin(cp);
        arp.setShowSql(true);// 控制台打印Sql
        SqlReporter.setLogger(true);// log4j 打印Sql
        me.add(arp);

        arp.setDialect(new MysqlDialect());
        // 配置属性名(字段名)大小写不敏感容器工厂
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());

        arp.addMapping("office", Office.class);
        arp.addMapping("user_login", UserLogin.class);
        arp.addMapping("role", Role.class);
        arp.addMapping("permission", Permission.class);
        arp.addMapping("user_role", UserRole.class);
        arp.addMapping("role_permission", RolePermission.class);
        
        arp.addMapping("leads", Leads.class);
        arp.addMapping("support_case", Case.class);
        
        arp.addMapping("order_header", Order.class);
        arp.addMapping("order_item", OrderItem.class);
        arp.addMapping("party", Party.class);
        arp.addMapping("party_attribute", PartyAttribute.class);
        arp.addMapping("dp_prof_provider_info", ServiceProvider.class);

        arp.addMapping("contact", Contact.class);        
        arp.addMapping("fin_account", Account.class);
        
        arp.addMapping("fin_item", Toll.class);        
        arp.addMapping("route", Route.class);
        arp.addMapping("product", Product.class);
        arp.addMapping("category", Category.class);
        arp.addMapping("warehouse", Warehouse.class);
        arp.addMapping("contract_item", ContractItem.class);
        arp.addMapping("order_status", OrderStatus.class);
        arp.addMapping("contract", Contract.class);
        arp.addMapping("transfer_order", TransferOrder.class);
        arp.addMapping("transfer_order_item", TransferOrderItem.class);
        arp.addMapping("transfer_order_item_detail", TransferOrderItemDetail.class);
        arp.addMapping("return_order", ReturnOrder.class);
        arp.addMapping("return_order_fin_item", ReturnOrderFinItem.class);
        arp.addMapping("delivery_order", DeliveryOrder.class);
        arp.addMapping("transfer_order_milestone", TransferOrderMilestone.class);
        arp.addMapping("billing_order", BillingOrder.class);
        arp.addMapping("billing_order_item", BillingOrderItem.class);
        arp.addMapping("delivery_order_milestone", DeliveryOrderMilestone.class);
        arp.addMapping("location", Location.class);
        //配送调车单
        arp.addMapping("delivery_plan_order", DeliveryPlanOrder.class);
        arp.addMapping("delivery_plan_order_detail", DeliveryPlanOrderDetail.class);
        arp.addMapping("delivery_plan_order_milestone", DeliveryPlanOrderMilestone.class);
        arp.addMapping("delivery_plan_order_carinfo", DeliveryPlanOrderCarinfo.class);
        //arp.addMapping("fin_account_item", AccountItem.class);
        arp.addMapping("delivery_order_item", DeliveryOrderItem.class);
        arp.addMapping("depart_order", DepartOrder.class);
        arp.addMapping("depart_transfer", DepartTransferOrder.class);
        arp.addMapping("depart_pickup", DepartPickupOrder.class);
        arp.addMapping("warehouse_order", WarehouseOrder.class);
        arp.addMapping("warehouse_order_item", WarehouseOrderItem.class);
        arp.addMapping("inventory_item", InventoryItem.class);
        arp.addMapping("carinfo", Carinfo.class);
        arp.addMapping("fin_item", Fin_item.class);
        arp.addMapping("delivery_order_fin_item", DeliveryOrderFinItem.class);
        arp.addMapping("transfer_order_fin_item", TransferOrderFinItem.class);
        arp.addMapping("depart_order_fin_item", DepartOrderFinItem.class);//提货拼车单、发车单的应付表
        arp.addMapping("pickup_order_fin_item", PickupOrderFinItem.class);//提货拼车单、发车单的应付表
        arp.addMapping("arap_charge_order", ArapChargeOrder.class);
        arp.addMapping("arap_charge_item", ArapChargeItem.class);
        arp.addMapping("arap_charge_invoice", ArapChargeInvoice.class);
        arp.addMapping("insurance_order", InsuranceOrder.class);
        arp.addMapping("insurance_fin_item", InsuranceFinItem.class);
        
        arp.addMapping("arap_charge_invoice_application_order", ArapChargeInvoiceApplication.class);
        arp.addMapping("arap_charge_invoice_application_item", ArapChargeInvoiceApplicationItem.class);
        arp.addMapping("arap_charge_invoice_item_invoice_no", ArapChargeInvoiceItemInvoiceNo.class);
        arp.addMapping("arap_charge_application_invoice_no", ArapChargeApplicationInvoiceNo.class);
        arp.addMapping("arap_misc_charge_order", ArapMiscChargeOrder.class);
        arp.addMapping("arap_misc_charge_order_item", ArapMiscChargeOrderItem.class);
        arp.addMapping("arap_account_audit_log", ArapAccountAuditLog.class);
        // 应付对账单
        arp.addMapping("arap_cost_order", ArapCostOrder.class);
        arp.addMapping("arap_cost_item", ArapCostItem.class);
        arp.addMapping("arap_cost_invoice_application_order", ArapCostInvoiceApplication.class);
        arp.addMapping("arap_cost_invoice", ArapCostInvoice.class);
        arp.addMapping("arap_cost_invoice_item_invoice_no", ArapCostInvoiceItemInvoiceNo.class);
        arp.addMapping("arap_cost_order_invoice_no", ArapCostOrderInvoiceNo.class);
        arp.addMapping("arap_misc_cost_order", ArapMiscCostOrder.class);
        arp.addMapping("arap_misc_cost_order_item", models.yh.arap.ArapMiscCostOrderItem.class);
        //应付报销单
        arp.addMapping("reimbursement_order", ReimbursementOrder.class);
        arp.addMapping("reimbursement_order_fin_item", ReimbursementOrderFinItem.class);
        // yh mapping
        //行车单
        arp.addMapping("car_summary_order", CarSummaryOrder.class);
        arp.addMapping("car_summary_detail", CarSummaryDetail.class);
        arp.addMapping("car_summary_detail_route_fee", CarSummaryDetailRouteFee.class);
        arp.addMapping("car_summary_detail_oil_fee", CarSummaryDetailOilFee.class);
        arp.addMapping("car_summary_detail_salary", CarSummaryDetailSalary.class);
        arp.addMapping("car_summary_detail_other_fee", CarSummaryDetailOtherFee.class);
        //基本数据用户网点
        arp.addMapping("user_office", UserOffice.class);
        arp.addMapping("user_customer", UserCustomer.class);
        
        arp.addMapping("customize_field", CustomizeField.class);
        arp.addMapping("office_config", OfficeCofig.class);
        //单据附件上传
        arp.addMapping("order_attachment_file", OrderAttachmentFile.class);
    }

    private void initDBconnector() {
        String dbType = getProperty("dbType");
        String url = getProperty("dbUrl");
        String username = getProperty("username");
        String pwd = getProperty("pwd");

        if (H2.equals(dbType)) {
            connectH2();
        } else {
            cp = new C3p0Plugin(url, username, pwd);
            //DataInitUtil.initH2Tables(cp);

        }

    }

    private void connectH2() {
        // 这个启动web console以方便通过localhost:8082访问数据库
        try {
            Server.createWebServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cp = new C3p0Plugin("jdbc:h2:mem:eeda;", "sa", "");
        // cp = new C3p0Plugin("jdbc:h2:data/sample;IFEXISTS=TRUE;", "sa", "");
        cp.setDriverClass("org.h2.Driver");
        DataInitUtil.initH2Tables(cp);
    }

    @Override
	public void configInterceptor(Interceptors me) {
    	if("Y".equals(getProperty("is_check_permission"))){
    		logger.debug("is_check_permission = Y");
         	me.add(new ShiroInterceptor());
    	}
        //me.add(new SetAttrLoginUserInterceptor());
    }

    @Override
	public void configHandler(Handlers me) {
        if (H2.equals(getProperty("dbType"))) {
            DataInitUtil.initData(cp);
        }
        //DataInitUtil.initData(cp);
        
        
        me.add(new UrlHanlder());
        
    }
}
