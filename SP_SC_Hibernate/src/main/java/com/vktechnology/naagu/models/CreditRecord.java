package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_record")
public class CreditRecord implements Serializable{

	private long 	Cr_Id;
	private String 	Cr_Date;
	private String 	Cr_Name;
	private BigDecimal 	Cr_Withdraw;
	private BigDecimal 	Cr_A_Balance;
	private BigDecimal 	Cr_C_Balance;
	private String 	Cr_Description;
	private String 	Cr_User;
	
	
	@Id
    @GeneratedValue
    @Column(name="Cr_Id")
	public long getCr_Id() {
		return Cr_Id;
	}
	public void setCr_Id(long cr_Id) {
		Cr_Id = cr_Id;
	}
	
	@Column(name="Cr_Date")
	public String getCr_Date() {
		return Cr_Date;
	}
	public void setCr_Date(String cr_Date) {
		Cr_Date = cr_Date;
	}
	
	@Column(name="Cr_Name")
	public String getCr_Name() {
		return Cr_Name;
	}
	public void setCr_Name(String cr_Name) {
		Cr_Name = cr_Name;
	}
	
	@Column(name="Cr_Withdraw")
	public BigDecimal getCr_Withdraw() {
		return Cr_Withdraw;
	}
	public void setCr_Withdraw(BigDecimal cr_Withdraw) {
		Cr_Withdraw = cr_Withdraw;
	}
	
	@Column(name="Cr_A_Balance")
	public BigDecimal getCr_A_Balance() {
		return Cr_A_Balance;
	}
	public void setCr_A_Balance(BigDecimal cr_Balance) {
		Cr_A_Balance = cr_Balance;
	}
	
	
	
	@Column(name="Cr_C_Balance")
	public BigDecimal getCr_C_Balance() {
		return Cr_C_Balance;
	}
	public void setCr_C_Balance(BigDecimal cr_SourceBalance) {
		Cr_C_Balance = cr_SourceBalance;
	}
	
	@Column(name="Cr_Description")
	public String getCr_Description() {
		return Cr_Description;
	}
	public void setCr_Description(String cr_Description) {
		Cr_Description = cr_Description;
	}
	
	@Column(name="Cr_User")
	public String getCr_User() {
		return Cr_User;
	}
	public void setCr_User(String cr_User) {
		Cr_User = cr_User;
	}
	
	
}
