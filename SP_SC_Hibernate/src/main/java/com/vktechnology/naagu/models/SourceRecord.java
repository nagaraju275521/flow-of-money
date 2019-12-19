package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "source_record")
public class SourceRecord implements Serializable{
	
	private long 	Sr_Id;
	private String 	Sr_Date;
	private String 	Sr_Name;
	private BigDecimal 	Sr_Deposits;
	private BigDecimal	Sr_Balance;
	private String 	Sr_Description;
	private String	Sr_User;
	
	@Id
	@GeneratedValue
	@Column(name="Sr_Id")
	public long getSr_Id() {
		return Sr_Id;
	}
	public void setSr_Id(long sr_Id) {
		Sr_Id = sr_Id;
	}
	
	@Column(name="Sr_Date")
	public String getSr_Date() {
		return Sr_Date;
	}
	public void setSr_Date(String sr_Date) {
		Sr_Date = sr_Date;
	}
	
	@Column(name="Sr_Name")
	public String getSr_Name() {
		return Sr_Name;
	}
	public void setSr_Name(String sr_Name) {
		Sr_Name = sr_Name;
	}
	
	@Column(name="Sr_Deposits")
	public BigDecimal getSr_Deposits() {
		return Sr_Deposits;
	}
	public void setSr_Deposits(BigDecimal sr_Deposits) {
		Sr_Deposits = sr_Deposits;
	}
	
	@Column(name="Sr_Balance")
	public BigDecimal getSr_Balance() {
		return Sr_Balance;
	}
	public void setSr_Balance(BigDecimal sr_Balance) {
		Sr_Balance = sr_Balance;
	}
	
	@Column(name="Sr_Description")
	public String getSr_Description() {
		return Sr_Description;
	}
	public void setSr_Description(String sr_Description) {
		Sr_Description = sr_Description;
	}
	
	@Column(name="Sr_User")
	public String getSr_User() {
		return Sr_User;
	}
	public void setSr_User(String sr_User) {
		Sr_User = sr_User;
	}
	
	
}
