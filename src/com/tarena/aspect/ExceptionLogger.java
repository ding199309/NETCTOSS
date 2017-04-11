package com.tarena.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tarena.entity.Admin;

/**
 * 记录系统异常日志
 * @author student
 *
 */

@Component
@Aspect
public class ExceptionLogger {
	/**
	 * 此处注入了request，会导致执行测试代码都报错。
	 * 主要是以为测试代码执行时没有启动tomcat，没有
	 * 创建出request对象，无法注入导致的。
	 * 可以在测试之前将此处的@Resource暂时注释掉。
	 */
	@Resource
	private HttpServletRequest request;
	@Around("within(com.tarena.web..*)")
	public Object  log(ProceedingJoinPoint p) throws Throwable{
		Object obj=null;
				try {					
					//调用目标组件
					obj=p.proceed();
				} catch (Throwable e) {
					Admin admin=(Admin)request
						.getSession().getAttribute("admin");
					String adminCode=admin.getAdmin_code();
					String ip=request.getRemoteHost();
					String now=new SimpleDateFormat("yyyy-MM-dd HH:mm;ss").
									format(new Date());
					String className=p.getTarget().getClass().getName();
					String methodName=p.getSignature().getName();
					
					Logger logger=Logger.getLogger(Exception.class);
					//异常信息标题
					logger.error("用户["+adminCode+"]," +
							"IP["+ip+"],在["+now+"]," +
							"执行["+className+"."+methodName+"]时," +
									"发生如下异常：");
					//循环记录每一行异常信息
					StackTraceElement[] elems=e.getStackTrace();
					
					for (StackTraceElement elem : elems) {
						logger.error("/t"+elem.toString());
						
					}
					
					throw e;
				}
		return obj;
	}

}
