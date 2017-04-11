package com.tarena.web;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.CostMapper;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

@Controller
@RequestMapping("/cost")

@SessionAttributes("costPage")//������Ϊsession����
public class CostController extends BaseController implements Serializable {
	
	
	@Resource
	private CostMapper costMapper;
	
	@RequestMapping("/findCost.do")
	public String action1(CostPage page, ModelMap map){
		
		List<Cost> list=costMapper.findByPage(page);
		
		map.put("list", list);
		int rows=costMapper.findRows();
		page.setRows(rows);
		map.put("costPage", page);
		
		return "cost/cost_list";
	}
	
	
	@RequestMapping("/toAddCost.do")
	public String toAdd(){
		//û���κ�ҵ��ֱ��ת��������ҳ��
		return "cost/add_cost";
	}
	
	/**
	 * ��������
	 */
	@RequestMapping("/addCost.do")
	public String add(Cost cost){
		
		//�������ʷ��ǣ�״̬Ĭ��Ϊ��ͣ
		cost.setStatus("1");
		
		//����ʱ��Ϊ��ǰϵͳ��ʱ��
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
			  costMapper.save(cost);
		
		return "redirect:findCost.do";
		
	}
	
//	@RequestMapping("/deleteCost.do")
//	public  String delete(Cost cost){
//		
//		costMapper.delete(cost);
//		
//		return "redirect:findCost.do";
//	}
	
	@RequestMapping("/toUpdateCost.do")
	public String toUpdate(int id,Model model){
		Cost c=costMapper.findByid(id);
			model.addAttribute("cost",c);
		
		return "cost/update_cost";
		
	}
	@RequestMapping("/updateCost.do")
	public String upDate(Cost cost ){
		costMapper.update(cost);	
		return "redirect:findCost.do";
	}
	@RequestMapping("/deleteCost.do")
	public String delete(int id){
		costMapper.delete(id);
		return "redirect:findCost.do";
	}

}
