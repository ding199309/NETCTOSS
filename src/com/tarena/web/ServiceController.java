package com.tarena.web;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.annotation.Mapper;
import com.tarena.dao.AccountMapper;
import com.tarena.dao.CostMapper;
import com.tarena.dao.ServiceMapper;
import com.tarena.entity.Account;
import com.tarena.entity.Cost;
import com.tarena.entity.Service;
import com.tarena.entity.page.ServicePage;


@Controller
@RequestMapping("/service")
@SessionAttributes("servicePage")
public class ServiceController extends BaseController implements Serializable{
	
	@Resource
	private ServiceMapper srmapper;
	
	@Resource
	private AccountMapper  acmapper;
	
	@Resource
	private CostMapper  comapper;
	
	@RequestMapping("/findService.do")
	public String findService(ServicePage page,Model model){
		
		List<Map<String,Object>> list=srmapper.findByPage(page);
				
		model.addAttribute("services",list);		
		int r=srmapper.findRows(page);
		page.setRows(r);		
		model.addAttribute("servicePage",page);
		return "service/service_list";
		
	}
	@RequestMapping("/startService.do")
	@ResponseBody
	public Map<String,Object> start(int id){
		 Map<String,Object> result=new HashMap<String, Object>();
		 	Service service=srmapper.findById(id);
		 	
		 	Account account=acmapper.findById(service.getAccount_id());
		
		 if(account.getStatus().equals("0")){
		 
			srmapper.start(id);
			result.put("success", true);//执行成功
			result.put("message", "开通成功.");//提示		
			
		 }else{
			 result.put("success", false);//执行成功
				result.put("message", "失败");//提示									 
		 }
		return result;
	}
	
	
	@RequestMapping("/pauseService.do")
	@ResponseBody
	public Map<String,Object> pause(int id){
		 Map<String,Object> result=new HashMap<String, Object>();
		 	srmapper.pause(id);
		 	
		 	result.put("success", true);
		 	result.put("message", "暂停成功");						
		return result;
		
	}
	@RequestMapping("/deleteService.do")
	@ResponseBody
	public Map<String,Object>delete(int id){
		 Map<String,Object> result=new HashMap<String, Object>();
		 	srmapper.delete(id);
			result.put("success", true);
		 	result.put("message", "删除成功");	
		return result;
		
	}
	
	@RequestMapping("/toAddService.do")
	public String toAdd(Model model){
		
		List<Cost> list=comapper.findAllCost();
		model.addAttribute("costs",list);
		return "service/service_add";
	}
	
	
	@RequestMapping("/searchAccount.do")
	@ResponseBody
	public Account searchAccount(String idcardNo){		
		//{"account_id":1011,"login_name":"zs"...}
		//System.out.println(11111111);
		return acmapper.findByIdcardNo(idcardNo);
	}
	
	@RequestMapping("/addService.do")
	public String addService(Service service){
		
		service.setStatus("1");
		service.setCreate_date(new Timestamp(System.currentTimeMillis()));
		srmapper.addService(service);
		
		return "redirect:findService.do";
		
	}
	
	@RequestMapping("/toUpdateService.do")
	public String toUpdate(int id,Model model){
		Service service=srmapper.findById(id);
		
		model.addAttribute("services",service);
		
		List<Cost> list=comapper.findAllCost();
		
		model.addAttribute("costs",list);
		
		return "/service/service_update";
	}
	
	@RequestMapping("/updateService.do")
	public String updateService(Service service){
		srmapper.update(service);		
		return "redirect:findService.do";
	}
	

}
