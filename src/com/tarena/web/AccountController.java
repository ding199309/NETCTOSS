package com.tarena.web;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.AccountMapper;
import com.tarena.dao.ServiceMapper;
import com.tarena.entity.Account;
import com.tarena.entity.Service;
import com.tarena.entity.page.AccountPage;



@Controller
@RequestMapping("/account")
@SessionAttributes("accountPage")
public class AccountController  extends BaseController implements Serializable{
	
	@Resource
	private  AccountMapper acmapper;
	
	@Resource
	private ServiceMapper srmapper;
	
	@RequestMapping("/findAccount.do")
	public String findByPage(AccountPage page,ModelMap map){
		
		List<Account> att=acmapper.findByPage(page);		
		map.put("accounts", att);
		
		int i=acmapper.findRows(page);		
		page.setRows(i);		
		map.put("accountPage", page);		
		return "account/account_list";
	
	}
	
	@RequestMapping("/pauseAccount.do")
	public String  pauseAccount(Integer id){	
		acmapper.pause(id);
		
		srmapper.pauseByAccountId(id);
		
	
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/stateAccount.do")
	public String stateAccount(Integer id){
			acmapper.start(id);
		return "redirect:findAccount.do";
		
	}
	@RequestMapping("/deleteAccount.do")
	public String delete(Integer id){
		
		acmapper.delete(id);
		srmapper.deleteByAccountId(id);
		
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toAddAccount.do")
	public String toAddAccount(){
		
		
		return "account/account_add";
	}
	
	@RequestMapping("/addAccount.do")
	public String addAccount(Account account){
		account.setStatus("1");
		account.setCreate_date(new Timestamp(System.currentTimeMillis()));		
		acmapper.insert(account);		
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toUpdateAccount.do")
	public String toUpdateAccount(int id,ModelMap  map){
		Account a =acmapper.findById(id);
		
		map.put("accounts", a);
		return "account/account_update";
		
	}
	
	@RequestMapping("/updateAccount.do")
	public String updateAccount(Account account){
		
		acmapper.update(account);
		
		return "redirect:findAccount.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
