package com.tarena.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.CostMapper;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

public class TestCase {
	ApplicationContext ctx=
		new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//@Test
	public void testFindAllCost(){
		
		CostMapper costMapper=ctx.getBean(CostMapper.class);
		List<Cost> list=costMapper.findAllCost();
		
		for(Cost c:list){
			System.out.println(c);
		}
	}
	//@Test
	public void testSave(){
		CostMapper costMapper=ctx.getBean(CostMapper.class);
		Cost c=new Cost();
		c.setName("tarenaÌ×²Í");
		c.setBase_duration(90);
		c.setStatus("0");
		c.setCost_type("2");
		costMapper.save(c);
		System.out.println("ok");
	}
	
	//@Test
	public void test2(){
		System.out.println( new Timestamp(System.currentTimeMillis()));
		System.out.println(System.currentTimeMillis());
	}
	
	//@Test
	public void testFindBy(){
		CostMapper c=ctx.getBean(CostMapper.class);
		Cost cost=c.findByid(10000);
		System.out.println(cost);
	}
	
	//@Test
	public void testUpdate(){
		CostMapper c=ctx.getBean(CostMapper.class);
		Cost cost=c.findByid(104);
		System.out.println(cost);
		cost.setName("AAAAAAA");
		c.update(cost);
		System.out.println("ok");
	}
	//@Test
	public void testde(){
		CostMapper c=ctx.getBean(CostMapper.class);
	c.delete(10000);
		System.out.println("ok");
	}
	//@Test
	public void testFindByPage(){
		CostMapper  c=ctx.getBean(CostMapper.class);
		CostPage vo=new CostPage();
		vo.setCurrentPage(2);
		List<Cost> cost=c.findByPage(vo);
		for(Cost tt:cost){
			System.out.println(tt);
		}
	}
	@Test
	public void testRows(){
		CostMapper c=ctx.getBean(CostMapper.class);
		int s=c.findRows();
		System.out.println(s);
	}
	
	
}



