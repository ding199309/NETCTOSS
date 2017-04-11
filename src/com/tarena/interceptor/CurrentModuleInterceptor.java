package com.tarena.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



/**
 * �жϵ�ǰҳ���λ�õ�������
 * @author student
 *
 */
public class CurrentModuleInterceptor implements  HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		//��ȡ��������url
		String url=req.getRequestURL().toString();
		
		int m=0;//index
		if(url.contains("role")){
			m=1;
		}else if(url.contains("admin")){
			m=2;			
		}else if(url.contains("cost")){
			m=3;
		}else if(url.contains("account")){
			m=4;
		}else if(url.contains("service")){
			m=5;
		}
		
		//���жϽ������ǰģ��id����session
		req.getSession().setAttribute("currentModuleId",m);
		return true;
	}

}
