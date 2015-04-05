package config;

import handler.UrlHanlder;

import java.lang.management.ManagementFactory;
import java.sql.SQLException;

import models.Office;
import models.Party;
import models.PartyAttribute;
import models.Permission;
import models.Role;
import models.RolePermission;
import models.UserLogin;
import models.UserRole;
import models.eeda.Case;
import models.eeda.Leads;
import models.eeda.Order;
import models.eeda.OrderItem;
import models.eeda.ServiceProvider;

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
        arp.addMapping("user_roles", UserRole.class);
        arp.addMapping("role_permissions", RolePermission.class);
        
        arp.addMapping("leads", Leads.class);
        arp.addMapping("support_case", Case.class);
        
        arp.addMapping("order_header", Order.class);
        arp.addMapping("order_item", OrderItem.class);
        arp.addMapping("party", Party.class);
        arp.addMapping("party_attribute", PartyAttribute.class);
        arp.addMapping("DP_PROF_PROVIDER_INFO", ServiceProvider.class);

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
