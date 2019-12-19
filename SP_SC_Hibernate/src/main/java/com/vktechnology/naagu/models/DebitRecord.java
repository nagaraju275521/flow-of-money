package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "debit_record")
public class DebitRecord implements Serializable{
	
	private long    id;
	private String  dr_Date;
	private String  dr_Name;
	private BigDecimal   dr_Balance;
	private BigDecimal   dr_Withdraw;
	private String	dr_SourceName;
	private BigDecimal   dr_SourceBalance;
	private String  dr_Description;
	private String  dr_User;
	
	
	@Id
    @GeneratedValue
    @Column(name="Id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="Dr_Date")
	public String getDr_Date() {
		return dr_Date;
	}
	public void setDr_Date(String dr_Date) {
		this.dr_Date = dr_Date;
	}
	
	@Column(name="Dr_Name")
	public String getDr_Name() {
		return dr_Name;
	}
	public void setDr_Name(String dr_Name) {
		this.dr_Name = dr_Name;
	}
	
	@Column(name="Dr_Balance")
	public BigDecimal getDr_Balance() {
		return dr_Balance;
	}
	public void setDr_Balance(BigDecimal dr_Balance) {
		this.dr_Balance = dr_Balance;
	}
	
	@Column(name="Dr_Withdraw")
	public BigDecimal getDr_Withdraw() {
		return dr_Withdraw;
	}
	public void setDr_Withdraw(BigDecimal dr_Withdraw) {
		this.dr_Withdraw = dr_Withdraw;
	}
	
	@Column(name="Dr_SourceName")
	public String getDr_SourceName() {
		return dr_SourceName;
	}
	public void setDr_SourceName(String dr_SourceName) {
		this.dr_SourceName = dr_SourceName;
	}
	
	@Column(name="Dr_SourceBalance")
	public BigDecimal getDr_SourceBalance() {
		return dr_SourceBalance;
	}
	public void setDr_SourceBalance(BigDecimal dr_SourceBalance) {
		this.dr_SourceBalance = dr_SourceBalance;
	}
	
	@Column(name="Dr_Description")
	public String getDr_Description() {
		return dr_Description;
	}
	public void setDr_Description(String dr_Description) {
		this.dr_Description = dr_Description;
	}
	
	@Column(name="Dr_User")
	public String getDr_User() {
		return dr_User;
	}
	public void setDr_User(String dr_User) {
		this.dr_User = dr_User;
	}
	
		 

}
