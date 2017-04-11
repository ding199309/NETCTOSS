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
 * ��¼ϵͳ�쳣��־
 * @author student
 *
 */

@Component
@Aspect
public class ExceptionLogger {
	/**
	 * �˴�ע����request���ᵼ��ִ�в��Դ��붼����
	 * ��Ҫ����Ϊ���Դ���ִ��ʱû������tomcat��û��
	 * ������request�����޷�ע�뵼�µġ�
	 * �����ڲ���֮ǰ���˴���@Resource��ʱע�͵���
	 */
	@Resource
	private HttpServletRequest request;
	@Around("within(com.tarena.web..*)")
	public Object  log(ProceedingJoinPoint p) throws Throwable{
		Object obj=null;
				try {					
					//����Ŀ�����
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
					//�쳣��Ϣ����
					logger.error("�û�["+adminCode+"]," +
							"IP["+ip+"],��["+now+"]," +
							"ִ��["+className+"."+methodName+"]ʱ," +
									"���������쳣��");
					//ѭ����¼ÿһ���쳣��Ϣ
					StackTraceElement[] elems=e.getStackTrace();
					
					for (StackTraceElement elem : elems) {
						logger.error("/t"+elem.toString());
						
					}
					
					throw e;
				}
		return obj;
	}

}
