package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.Mapper;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.AdminPage;

@Mapper
public interface AdminMapper {
	
	List<Role> finds();
	
	List<Admin> findByPage(AdminPage page);
	
	int findRows(AdminPage page);
	
	
	/*
	 * 修改密码
	 * Map中封装了2个参数
	 * key                       value
	 * adminIds             List<Integer>
	 * defaultpassword           String
	 */

	void updatePassWord(Map<String,Object> param);
	
	
	
	Admin findByName(String name);
	
	
	/*通过连接，找出admin对应的模块*/
	List<Module> findByAdminId(Integer adminId);
	
	/*
	 * 保存到中间表
	 */
	void saveadminRole(Map<String,Object> param);
	/*新增加一个admin*/
	void save(Admin admin);
	
	void delete(int id);
	
	
	void deleteAdminRole(int id);
	
	
	/*通过id找到admin对象*/
	Admin findById(int id);
	
	
	void update(Admin admin);
	
	
}
