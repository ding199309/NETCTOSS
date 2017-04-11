package com.tarena.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *	�Զ������������ת���������Խ����е�
 *	�����ַ���ת����java.sql.Date
 */
public class DateEditor 
	extends PropertyEditorSupport {
	
	//Ĭ��֧�ֵ�ת�������ڸ�ʽ
	private String pattern = "yyyy-MM-dd";
	
	public DateEditor() {
		
	}
	
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}

	/* 
	 * �����е��ַ���ת���ɶ����е����ԡ�
	 * text�������Ǳ��е������ַ�����������Ҫ
	 * ����ת�ͳ�java.sql.Date�ٴ��ظ�Spring.
	 */
	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			//�������Ϊ�գ�ֱ�ӽ�null���ظ�Spring
			setValue(null);
		} else {
			//���������Ϊ�գ�����ת����java.sql.Date��
			//�ٴ���Spring
			SimpleDateFormat sf = 
				new SimpleDateFormat(this.pattern);
			String dateStr = 
				sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}

}