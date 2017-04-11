package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.Mapper;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.RolePage;

@Mapper
public interface RoleMapper {
	
	List<Role> findByPage(RolePage page);
		int findRows();
		
		
		/**
		 * 通常情况下，一张表应该有自己独立的
		 * Mapper接口和实现，但是由于module_info
		 * 表没有独立的功能，因此这里就省略了它
		 * 自己的Mapper，将这些内容写到使用的地方。
		 */
		List<Module> findAll();
		
		void save(Role role);
		
		/**
		 * 新增角色模块中间表。
		 * param是传入的参数，封装了中间表的数据，
		 * 代替了实体类。
		 * key					value
		 * role_id			1
		 * module_id		6
		 */
		void savamoduleRole(Map<String,Object> param);
		
		
		Role findByName(String name);
		
		Role findById(int id);
		void update(Role role);
		
		void delete(int id);
		void deleteModuleRole(int id);
		
		List<Role> findAllRole();
		
}
