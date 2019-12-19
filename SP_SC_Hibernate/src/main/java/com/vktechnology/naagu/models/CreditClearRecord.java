package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "creditclearrecord")
public class CreditClearRecord implements Serializable{
	
	private long CreditClear_Id;
    private String CreditClear_Date;
    private String CreditClear_name;
    private BigDecimal CreditClear_Amount;
    private BigDecimal CreditClear_balance;
    private String CreditClear_Source;
    private BigDecimal CreditClear_SourceBal;
    private String CreditClear_Des;
    private String CreditClear_User;
    
    @Id
    @GeneratedValue
    @Column(name="CreditClear_Id")
	public long getCreditClear_Id() {
		return CreditClear_Id;
	}
	public void setCreditClear_Id(long creditClear_Id) {
		CreditClear_Id = creditClear_Id;
	}
	
	@Column(name="CreditClear_Date")
	public String getCreditClear_Date() {
		return CreditClear_Date;
	}
	public void setCreditClear_Date(String creditClear_Date) {
		CreditClear_Date = creditClear_Date;
	}
	
	@Column(name="CreditClear_name")
	public String getCreditClear_name() {
		return CreditClear_name;
	}
	public void setCreditClear_name(String creditClear_name) {
		CreditClear_name = creditClear_name;
	}
	
	@Column(name="CreditClear_Amount")
	public BigDecimal getCreditClear_Amount() {
		return CreditClear_Amount;
	}
	public void setCreditClear_Amount(BigDecimal creditClear_Amount) {
		CreditClear_Amount = creditClear_Amount;
	}
	
	@Column(name="CreditClear_balance")
	public BigDecimal getCreditClear_balance() {
		return CreditClear_balance;
	}
	public void setCreditClear_balance(BigDecimal creditClear_balance) {
		CreditClear_balance = creditClear_balance;
	}
	
	@Column(name="CreditClear_Source")
	public String getCreditClear_Source() {
		return CreditClear_Source;
	}
	public void setCreditClear_Source(String creditClear_Source) {
		CreditClear_Source = creditClear_Source;
	}
	
	@Column(name="CreditClear_SourceBal")
	public BigDecimal getCreditClear_SourceBal() {
		return CreditClear_SourceBal;
	}
	public void setCreditClear_SourceBal(BigDecimal creditClear_SourceBal) {
		CreditClear_SourceBal = creditClear_SourceBal;
	}
	
	@Column(name="CreditClear_Des")
	public String getCreditClear_Des() {
		return CreditClear_Des;
	}
	public void setCreditClear_Des(String creditClear_Des) {
		CreditClear_Des = creditClear_Des;
	}
	
	@Column(name="CreditClear_User")
	public String getCreditClear_User() {
		return CreditClear_User;
	}
	public void setCreditClear_User(String creditClear_User) {
		CreditClear_User = creditClear_User;
	}

    
}
