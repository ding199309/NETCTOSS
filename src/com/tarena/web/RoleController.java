package com.tarena.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.RoleMapper;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.RolePage;

@Controller
@RequestMapping("/role")
@SessionAttributes("rolePage")
public class RoleController extends BaseController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RoleMapper roleMapper;
	
	@RequestMapping("/findRole.do")
	public String findRole(RolePage page ,ModelMap map){
		
		List<Role> list=roleMapper.findByPage(page);
		map.put("roles", list);		
		int r=roleMapper.findRows();
		page.setRows(r);		
		map.put("rolePage", page);		
		return "role/role_list";
		
	}
	
	
	@RequestMapping("/toAddRole.do")
	public String toAddRole(ModelMap map){
		
		List<Module> list=roleMapper.findAll();
		map.put("modules", list);
		//Integer.valueOf("aaa");
		return "role/role_add";
	}
	
	@RequestMapping("/addRole.do")
	public String addRole(Role role){
		//新增角色
		roleMapper.save(role);
		//新增角色模块中间表
		List<Integer>  modulesId=role.getModulesId();
		
		if(modulesId!=null&&modulesId.size()>0){
			for (Integer moduleId : modulesId) {
				Map<String,Object> param=new HashMap<String,Object>();
				param.put("role_id", role.getRole_id());
				param.put("module_id", moduleId);
				roleMapper.savamoduleRole(param);				
			}
			
		}
		
		return "redirect:findRole.do";
	}
	
	@RequestMapping("checkRoleName.do")
	@ResponseBody
	public boolean checkroleMapp(String name){
		Role rol=roleMapper.findByName(name);
		System.out.println(1111);
		System.out.println(rol);
		if(rol==null){
			return true;
		}else{
			System.out.println(222);
		return false;
		}
	}
	
	@RequestMapping("/toUpdateRole.do")
	public String toUpdate(int id ,ModelMap map){
		
		Role role=roleMapper.findById(id);
		//System.out.println(role.getRole_id()+" "+role.getName());
		map.put("roles", role);
		
		List<Module> list=roleMapper.findAll();
			map.put("modules", list);
		return "role/role_update";
		
	}

	@RequestMapping("/updateRole.do")
	public String toUpdate(Role role){	
		//System.out.println(111);
		roleMapper.update(role);
		roleMapper.deleteModuleRole(role.getRole_id());		
		//System.out.println(222);
		
		List<Integer> x=role.getModulesId();
		for (Integer moduleId : x) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("role_id", role.getRole_id());
			map.put("module_id", moduleId);
			roleMapper.savamoduleRole(map);
			
		}
		
		
		return "redirect:findRole.do";
	}
	
	@RequestMapping("/deleteRole.do")
	public String deleteRole(int id){
		
		roleMapper.deleteModuleRole(id);
		roleMapper.delete(id);
		
		
		return "redirect:findRole.do";
	}
	
	
	

}
