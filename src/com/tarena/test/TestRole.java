package com.tarena.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.RoleMapper;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.RolePage;


public class TestRole {
	ApplicationContext ctx=
		new ClassPathXmlApplicationContext("applicationContext.xml");
	RoleMapper mapper=ctx.getBean(RoleMapper.class);
	//@Test
	public void testFindByPage(){
		
		RolePage page=new RolePage();
		
		List<Role> list=mapper.findByPage(page);
		for (Role role : list) {
			System.out.println(role.getRole_id()+" "+role.getName());
			
			List<Module> list2=role.getModules();
			for (Module module : list2) {
				System.out.println(module.getModule_id()+" "+module.getName());
			}
			System.out.println("--------------------------");
		}
	}
	
	@Test
	public void testAdd(){
		Role role=new Role();
		role.setName("´óÍ·");
		mapper.save(role);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("role_id", role.getRole_id());
		map.put("module_id",4);
		mapper.savamoduleRole(map);
	}
	
	
	
	
	
	
	
	
	

}
