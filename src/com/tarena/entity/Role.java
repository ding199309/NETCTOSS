package com.tarena.entity;

import java.io.Serializable;
import java.util.List;

public class Role  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer role_id;
	private String name;
	

	/**
	 * �������ԣ�������װ��ǰ��ɫ��Ӧ��һ��ģ�飬
	 * ����������MyBatis����ӳ���Զ�װ�䡣
	 */
	private List<Module> modules;
	/**
	 * ����/�޸ı���ʱ��������װҳ�洫���һ��IDֵ��
	 */
	private List<Integer> modulesId;
	
	
	public List<Integer> getModulesId() {
		return modulesId;
	}
	public void setModulesId(List<Integer> modulesId) {
		this.modulesId = modulesId;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
