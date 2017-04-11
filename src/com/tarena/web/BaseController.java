package com.tarena.web;




import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.tarena.util.DateEditor;

/*
 * ��Controller�ĸ��࣬���ڷ�װ������ҵ���߼�
 */
public class BaseController  implements WebBindingInitializer{

	
	/*
	 * binder�������Ը�ָ�����������Ͱ�����ת������
	 * ���ǿ������������޸���������ת����
	 * 
	 */
	
	//@InitBinderע�����ø÷���������ʼʱ����һ��
	//����Controller����ִ��ǰ���õ�
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
		//��java.sql.Date���Ͱ��Զ���ת����CustomDateEditor��
		//��ת������Spring�ṩ��Ҳ�����Լ�д��
		//��ת������2����������1��������SimpleDateFormat��
		//����ָ�����ڵĸ�ʽ����2���������Ƿ�Ϊ�ա�
		
		binder.registerCustomEditor(java.sql.Date.class,				
						new DateEditor());
		//System.out.println("initBinder");

		
	}

}
