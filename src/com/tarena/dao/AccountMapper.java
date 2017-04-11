package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.Mapper;
import com.tarena.entity.Account;
import com.tarena.entity.page.AccountPage;

@Mapper
public interface AccountMapper {	
	List<Account> findByPage(AccountPage page);
	int findRows(AccountPage page);
	
	/**
	 * ÔİÍ£Ò»ÌõÕËÎñÕËºÅ
	 */
	void pause(Integer id);
	void start(Integer id);
	void delete(Integer id);
	void insert(Account account);
	void update(Account account);
	Account findById(Integer id);
	
	Account findByIdcardNo(String idCardNo);
	
	
}
