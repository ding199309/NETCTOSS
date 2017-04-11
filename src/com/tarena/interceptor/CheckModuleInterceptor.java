package com.tarena.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Module;

public class CheckModuleInterceptor  implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		
	List<Module> list=(List<Module>) req.getSession().getAttribute("adminModules");
					Integer currentModuleId=(Integer) req.getSession().getAttribute("currentModuleId");
		for(Module e: list){
			//如果当前页面有权限
			if(e.getModule_id()==currentModuleId){				
				return true;
			}			
		}
		//如果有限制，没有当前页面
		res.sendRedirect(req.getContextPath()+"/login/nopower.do");
		return false;
	}

}
