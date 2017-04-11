package com.tarena.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.ServiceMapper;
import com.tarena.entity.page.ServicePage;

public class testService {
	ApplicationContext ctx=
		new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	
	@Test
	public void test1(){
		
		ServiceMapper mapper =ctx.getBean(ServiceMapper.class);
		ServicePage page=new ServicePage();
		page.setOsUserName("guojing");
		List<Map<String,Object>> list=mapper.findByPage(page);
		for(Map<String, Object> map : list) {
			System.out.println(
				map.get("SERVICE_ID")	+ " " +
				map.get("OS_USERNAME") + " " +
				map.get("UNIX_HOST") + " " +
				map.get("IDCARD_NO") + " " +
				map.get("NAME")
			);
		}
		int rows=mapper.findRows(page);
		System.out.println(rows);
	}

}
