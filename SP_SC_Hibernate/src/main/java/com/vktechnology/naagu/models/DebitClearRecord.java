package com.vktechnology.naagu.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "debitclearrecord")
public class DebitClearRecord {
	
	private long debitClear_Id;
    private String debitClear_Date;
    private String debitClear_name;
    private BigDecimal debitClear_Amount;
    private BigDecimal debitClear_balance;
    private String debitClear_Source;
    private BigDecimal debitClear_SourceBal;
    private String debitClear_Des;
    private String debitClear_User;
    
    @Id
    @GeneratedValue
    @Column(name="DebitClear_Id")
	public long getDebitClear_Id() {
		return debitClear_Id;
	}
	public void setDebitClear_Id(long debitClear_Id) {
		this.debitClear_Id = debitClear_Id;
	}
	
	@Column(name="DebitClear_Date")
	public String getDebitClear_Date() {
		return debitClear_Date;
	}
	public void setDebitClear_Date(String debitClear_Date) {
		this.debitClear_Date = debitClear_Date;
	}
	
	@Column(name="DebitClear_name")
	public String getDebitClear_name() {
		return debitClear_name;
	}
	public void setDebitClear_name(String debitClear_name) {
		this.debitClear_name = debitClear_name;
	}
	
	@Column(name="DebitClear_Amount")
	public BigDecimal getDebitClear_Amount() {
		return debitClear_Amount;
	}
	public void setDebitClear_Amount(BigDecimal debitClear_Amount) {
		this.debitClear_Amount = debitClear_Amount;
	}
	
	@Column(name="DebitClear_balance")
	public BigDecimal getDebitClear_balance() {
		return debitClear_balance;
	}
	public void setDebitClear_balance(BigDecimal debitClear_balance) {
		this.debitClear_balance = debitClear_balance;
	}
	
	@Column(name="DebitClear_Source")
	public String getDebitClear_Source() {
		return debitClear_Source;
	}
	public void setDebitClear_Source(String debitClear_Source) {
		this.debitClear_Source = debitClear_Source;
	}
	
	@Column(name="DebitClear_SourceBal")
	public BigDecimal getDebitClear_SourceBal() {
		return debitClear_SourceBal;
	}
	public void setDebitClear_SourceBal(BigDecimal debitClear_SourceBal) {
		this.debitClear_SourceBal = debitClear_SourceBal;
	}
	
	@Column(name="DebitClear_Des")
	public String getDebitClear_Des() {
		return debitClear_Des;
	}
	public void setDebitClear_Des(String debitClear_Des) {
		this.debitClear_Des = debitClear_Des;
	}
	
	@Column(name="DebitClear_User")
	public String getDebitClear_User() {
		return debitClear_User;
	}
	public void setDebitClear_User(String debitClear_User) {
		this.debitClear_User = debitClear_User;
	}
    
    
    
    

}
