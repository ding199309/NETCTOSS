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
	 * �޸�����
	 * Map�з�װ��2������
	 * key                       value
	 * adminIds             List<Integer>
	 * defaultpassword           String
	 */

	void updatePassWord(Map<String,Object> param);
	
	
	
	Admin findByName(String name);
	
	
	/*ͨ�����ӣ��ҳ�admin��Ӧ��ģ��*/
	List<Module> findByAdminId(Integer adminId);
	
	/*
	 * ���浽�м��
	 */
	void saveadminRole(Map<String,Object> param);
	/*������һ��admin*/
	void save(Admin admin);
	
	void delete(int id);
	
	
	void deleteAdminRole(int id);
	
	
	/*ͨ��id�ҵ�admin����*/
	Admin findById(int id);
	
	
	void update(Admin admin);
	
	
}
