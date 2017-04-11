package com.tarena.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.AdminMapper;
import com.tarena.dao.RoleMapper;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.AdminPage;

public class TestAdmin {
	ApplicationContext ctx=
		new ClassPathXmlApplicationContext("applicationContext.xml");
	AdminMapper mapper=ctx.getBean(AdminMapper.class);
	
	
	@Test
	public void testFindByPage(){
		
		AdminPage page=new AdminPage();
		
		List<Admin> list=mapper.findByPage(page);
		for(Admin a:list){
			System.out.println(a.getAdmin_id()+"  "+a.getAdmin_code());
			
			List<Role> r=a.getRoles();
			for(Role t:r){
				System.out.println(t.getName());
			}
			
			System.out.println("----------------------------");
			
		}
	}
	
	@Test
	public void teste(){
			RoleMapper roleMapper=ctx.getBean(RoleMapper.class);
		List<Module> list=roleMapper.findAll();
		for (Module module : list) {
			System.out.println(module.getModule_id()+" "+module.getName());
			

		}
	}
	
	@Test
	public void testUpdatePassWord(){
		ApplicationContext ctx=
			new ClassPathXmlApplicationContext("applicationContext.xml");
		Map<String,Object> param=new HashMap<String,Object>();
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(6000);
		ids.add(7000);
		param.put("adminIds", ids);
		param.put("defaultPassWord", "sssss");
		mapper.updatePassWord(param);
	}
	
	@Test
	public void test1(){
	Admin a=	mapper.findByName("caocao");
	System.out.println(a.getName());
	}
	
	
	@Test
	public void test2(){
	
		
		Admin a=mapper.findById(5000);
		System.out.println(a.getName()+"  "+a.getAdmin_id());
		List<Role> list=a.getRoles();
	for (Role role : list) {
		System.out.println(role.getRole_id());
		
	}
	}
	

}
