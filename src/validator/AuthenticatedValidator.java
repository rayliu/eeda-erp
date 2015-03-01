package validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;


import org.apache.shiro.util.Factory;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;


public class AuthenticatedValidator extends Validator {
	
	
	@Override
	protected void handleError(Controller controller) {
		controller.redirect("/login");
	}

	@Override
	protected void validate(Controller controller) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");  
		// 创建SecurityManager (根据配置创建SecurityManager实例)  
		SecurityManager security = factory.getInstance();  
		SecurityUtils.setSecurityManager(security);  
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("AuthenticatedValidator Obj:"+this);
		//setShortCircuit(true);		
        if (!currentUser.isAuthenticated()) {
        	//这里调用addError， 才会跳转到handleError
            addError("AuthenticatedFailed", "当前访问的方法未登录！");
        }
	}



}
