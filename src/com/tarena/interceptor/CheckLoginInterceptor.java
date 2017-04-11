package com.tarena.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Admin;
/**
 * µÇÂ¼À¹½ØÆ÷
 * @author student
 *
 */
public class CheckLoginInterceptor 
				implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
				Object arg2) throws Exception {
			
		Admin a=(Admin)req.getSession().getAttribute("admin");
	
		if(a!=null){
			return true;
		}else{
			res.sendRedirect(req.getContextPath()+"/login/toLogin.do");
			
			return false;
		}				
	}	
	public void afterCompletion(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
		
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	
	}	


}
