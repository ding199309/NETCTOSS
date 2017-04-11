package com.tarena.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *	自定义的日期类型转换器，可以将表单中的
 *	日期字符串转换成java.sql.Date
 */
public class DateEditor 
	extends PropertyEditorSupport {
	
	//默认支持的转换的日期格式
	private String pattern = "yyyy-MM-dd";
	
	public DateEditor() {
		
	}
	
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}

	/* 
	 * 将表单中的字符串转换成对象中的属性。
	 * text参数就是表单中的日期字符串，我们需要
	 * 将其转型成java.sql.Date再传回给Spring.
	 */
	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			//如果参数为空，直接将null传回给Spring
			setValue(null);
		} else {
			//如果参数不为空，则将其转换成java.sql.Date，
			//再传回Spring
			SimpleDateFormat sf = 
				new SimpleDateFormat(this.pattern);
			String dateStr = 
				sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}

}