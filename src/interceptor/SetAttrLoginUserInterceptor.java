package interceptor;

import models.UserLogin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class SetAttrLoginUserInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			UserLogin user = UserLogin.dao.findFirst("select * from user_login where user_name=?",currentUser.getPrincipal());
			if(user.get("c_name") != null && !"".equals(user.get("c_name"))){
				ai.getController().setAttr("userId", user.get("c_name"));
			}else{
				ai.getController().setAttr("userId", currentUser.getPrincipal());
			}
		}
		ai.invoke();
	}

}
