package com.tarena.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.AccountMapper;
import com.tarena.entity.Account;
import com.tarena.entity.page.AccountPage;

public class testAccount {
	
	ApplicationContext ctx=
		new ClassPathXmlApplicationContext("applicationContext.xml");
	AccountMapper mapper=ctx.getBean(AccountMapper.class);
	//@Test
	public void testfind(){
		
			AccountPage page=new AccountPage();
			
			page.setIdcardNo("330682196903190613");
			page.setStatus("1");
			page.setLoginName("xl18z60");
			page.setRealName("guojing");
		List<Account> list=mapper.findByPage(page);
		for(Account c:list){
			System.out.println(c.getAccount_id()+" "+
								c.getLogin_name());		
		}
		
	}
	//@Test
	public void testFindRows(){
		AccountPage page=new AccountPage();
		
		page.setIdcardNo("330682196903190613");
		page.setStatus("1");
		page.setLoginName("xl18z60");
		page.setRealName("guojing");
		int i=mapper.findRows(page);
		System.out.println(i);
	}
	//@Test
	public void insert(){
		Account a=new Account();
		a.setReal_name("JIMM222");
		a.setIdcard_no("123456199309119354");
		a.setLogin_name("ding");
		a.setLogin_passwd("1234");
		a.setTelephone("13241280877");
		mapper.insert(a);
		System.out.println("ok");
		
	}
	//@Test
	public void testById(){
		Account a=mapper.findById(1005);
		System.out.println(a.getIdcard_no()+" "+a.getReal_name());
		
	}
	//@Test
	public void testUpdate(){
		Account a=mapper.findById(1005);
		a.setReal_name("J123");
		a.setIdcard_no("413026199309119354");
		
		mapper.update(a);
		System.out.println("ok");
		
	}
	
	@Test
	public void test3(){
			Account account=mapper.findByIdcardNo("330682196903190613");
			System.out.println(account.getAccount_id()+" "+account.getReal_name());
	}
	
	
}
