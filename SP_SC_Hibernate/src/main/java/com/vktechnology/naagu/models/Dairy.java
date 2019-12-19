package com.vktechnology.naagu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dairy")
public class Dairy {
	
	private long dairyId;
	private String dairyDate;
	private String dairyDes;
	private String dairyUser;
	
	@Id
    @GeneratedValue
    @Column(name="DairyId")
	public long getDairyId() {
		return dairyId;
	}
	public void setDairyId(long dairyId) {
		this.dairyId = dairyId;
	}
	
	@Column(name="DairyDate")
	public String getDairyDate() {
		return dairyDate;
	}
	public void setDairyDate(String dairyDate) {
		this.dairyDate = dairyDate;
	}
	
	@Column(name="DairyDes")
	public String getDairyDes() {
		return dairyDes;
	}
	public void setDairyDes(String dairyDes) {
		this.dairyDes = dairyDes;
	}
	
	
	@Column(name="DairyUser")
	public String getDairyUser() {
		return dairyUser;
	}
	public void setDairyUser(String dairyUser) {
		this.dairyUser = dairyUser;
	}
	
	
	
		
	
	

}
