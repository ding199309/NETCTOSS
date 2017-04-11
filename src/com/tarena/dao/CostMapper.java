package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.Mapper;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

@Mapper
public interface CostMapper {
	List<Cost> findAllCost();
	void save(Cost cost);
	void delete (int id);
	Cost findByid(int id);
	void update(Cost cost);
	List<Cost> findByPage(CostPage vo);
	int findRows();
}
