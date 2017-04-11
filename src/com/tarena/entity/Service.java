package com.tarena.entity;

import java.sql.Timestamp;

public class Service {
	private Integer service_id;
	private Integer account_id;
	private String unix_host;
	private String os_username;
	public Integer getService_id() {
		return service_id;
	}
	public void setService_id(Integer serviceId) {
		service_id = serviceId;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer accountId) {
		account_id = accountId;
	}
	public String getUnix_host() {
		return unix_host;
	}
	public void setUnix_host(String unixHost) {
		unix_host = unixHost;
	}
	public String getOs_username() {
		return os_username;
	}
	public void setOs_username(String osUsername) {
		os_username = osUsername;
	}
	public String getLogin_passwd() {
		return login_passwd;
	}
	public void setLogin_passwd(String loginPasswd) {
		login_passwd = loginPasswd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp createDate) {
		create_date = createDate;
	}
	public Timestamp getPause_date() {
		return pause_date;
	}
	public void setPause_date(Timestamp pauseDate) {
		pause_date = pauseDate;
	}
	public Timestamp getClose_date() {
		return close_date;
	}
	public void setClose_date(Timestamp closeDate) {
		close_date = closeDate;
	}
	public Integer getCost_id() {
		return cost_id;
	}
	public void setCost_id(Integer costId) {
		cost_id = costId;
	}
	private String login_passwd;
	private String status;
	private Timestamp create_date;
	private Timestamp pause_date;
	private Timestamp close_date;
	private Integer cost_id;

}
