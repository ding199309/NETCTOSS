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
		 * ͨ������£�һ�ű�Ӧ�����Լ�������
		 * Mapper�ӿں�ʵ�֣���������module_info
		 * ��û�ж����Ĺ��ܣ���������ʡ������
		 * �Լ���Mapper������Щ����д��ʹ�õĵط���
		 */
		List<Module> findAll();
		
		void save(Role role);
		
		/**
		 * ������ɫģ���м��
		 * param�Ǵ���Ĳ�������װ���м������ݣ�
		 * ������ʵ���ࡣ
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
