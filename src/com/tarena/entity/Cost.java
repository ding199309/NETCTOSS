package com.tarena.entity;


import java.io.Serializable;
import java.sql.Timestamp;



/**
 * 1.所有属性都用封装类型，可以为null；
 * 2.属性都与字段同名，可以简化Mapper.xml;
 * 3.日期类型使用java.sql.类型
 * 	java.sql.Date	yyyy-MM-dd
 * 	java.sql.Time	HH:mm:ss
 * 	java.sql.Timestamp	yyyy-MM-dd HH:mm:ss
 */

public class Cost implements Serializable{
	
	private  Integer cost_id;
	private  String name;
	private Integer base_duration;
	private Double base_cost;
	private Double unit_cost;
	private String status;
	private String descr;
	private Timestamp creatime;
	private Timestamp startime;
	private String cost_type;
	public Cost() {
		
	}
	
	public Integer getCost_id() {
		return cost_id;
	}
	public void setCost_id(Integer costId) {
		cost_id = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBase_duration() {
		return base_duration;
	}
	public void setBase_duration(Integer baseDuration) {
		base_duration = baseDuration;
	}
	public Double getBase_cost() {
		return base_cost;
	}
	public void setBase_cost(Double baseCost) {
		base_cost = baseCost;
	}
	public Double getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(Double unitCost) {
		unit_cost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Timestamp getStartime() {
		return startime;
	}
	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String costType) {
		cost_type = costType;
	}
	@Override
	public String toString() {
		return "Cost [base_cost=" + base_cost + ", base_duration="
				+ base_duration + ", cost_id=" + cost_id + ", cost_type="
				+ cost_type + ", creatime=" + creatime + ", descr=" + descr
				+ ", name=" + name + ", startime=" + startime + ", status="
				+ status + ", unit_cost=" + unit_cost + "]";
	}
	
	
}
