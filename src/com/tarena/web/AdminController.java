package com.tarena.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.AdminMapper;
import com.tarena.dao.RoleMapper;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.AdminPage;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
public class AdminController {
	
	@Resource
	private AdminMapper adminMapper;
	
	@Resource
	private RoleMapper roleMapper;

	public static final String xxx="200";
	
	@RequestMapping("/findAdmin.do")
	public String findByPage(AdminPage page ,Model model){
		
		List<Admin> list=adminMapper.findByPage(page);
		model.addAttribute("admins",list);		
		int r=adminMapper.findRows(page);
		page.setRows(r);
		
		model.addAttribute("adminPage",page);
		
		List<Module> s=roleMapper.findAll();
		model.addAttribute("modules",s);
		
		return "admin/admin_list";
	}
	
	
	@RequestMapping("/resetPassword.do")
	@ResponseBody
	public Map<String,Object> updatePassWord(String ids){
		//将页面传入的id字符串，切割并转换成id的集合
		List<Integer> idList = 
			new ArrayList<Integer>();
		String[] idArray=ids.split(",");
		for (String id : idArray) {
			idList.add(Integer.valueOf(id));
		}
		
		//构造参数
		Map<String,Object> param=new HashMap<String, Object>();
			param.put("adminIds", idList);
			param.put("defaultPassWord", xxx);
		adminMapper.updatePassWord(param);
		
		
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("success", true);
		result.put("message", "密码重置成功");
		
		return result;
		
	}

	@RequestMapping("/toAddAdmin.do")
	public String toAddAdmin(Model model){
		List<Role> list=roleMapper.findAllRole();
		
		model.addAttribute("Roles",list);
		return "admin/admin_add";
	}

	@RequestMapping("/addAdmin.do")
	public String addAdmin(Admin admin){
			admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));			
						adminMapper.save(admin);
			List<Integer> list=admin.getRoleIds();
			if(list!=null&&list.size()>0){
				for (Integer x : list) {
					Map<String,Object> param=new HashMap<String,Object>();
					param.put("admin_id", admin.getAdmin_id());
					param.put("role_id",x);
					adminMapper.saveadminRole(param);
				
				}
			
			}
		
		return "redirect:findAdmin.do";
	}

	/*删除表及中间表*/
	@RequestMapping("/deleteAdmin.do")
	public String delete(int id){
		
		adminMapper.deleteAdminRole(id);
		adminMapper.delete(id);		
		return "redirect:findAdmin.do";
		
	}
	
	@RequestMapping("/toUpdateAdmin.do")
	public String toUpdateAdmin(int id,Model model){
		
		List<Role> list=adminMapper.finds();
		
		model.addAttribute("roles",list);
				
		Admin a=adminMapper.findById(id);
		List<Role> list11=a.getRoles();
		for (Role role : list11) {
			
		}
		
		model.addAttribute("admin",a);
		
		return "admin/admin_update";
	}
	
	@RequestMapping("/updateAdmin.do")
	public String updateAdmin(Admin admin){
		adminMapper.deleteAdminRole(admin.getAdmin_id());
			adminMapper.update(admin);
		
			List<Integer> list=admin.getRoleIds();
			if(list!=null&&list.size()>0){
				for (Integer x : list) {
					Map<String,Object> param=new HashMap<String,Object>();
					param.put("admin_id", admin.getAdmin_id());
					param.put("role_id",x);
					adminMapper.saveadminRole(param);				
				}			
			}		
		return "redirect:findAdmin.do";
	}
}















