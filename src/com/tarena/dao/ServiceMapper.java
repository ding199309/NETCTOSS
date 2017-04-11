package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.Mapper;
import com.tarena.entity.Service;
import com.tarena.entity.page.ServicePage;

@Mapper
public interface  ServiceMapper {
	
	
	 List<Map<String,Object>>  findByPage(ServicePage page);
	
	 int findRows(ServicePage page);
	 
	 void start(int id);
	 void pause(int id);
	 void delete(int id);
	  Service findById(int id);
	  
		/**
		 * 根据账务账号ID暂停对应的service
		 */
		void pauseByAccountId(int accountId);
		
		void deleteByAccountId(int accountId);
		
		void addService (Service service);
		 
		void update(Service service);

}
