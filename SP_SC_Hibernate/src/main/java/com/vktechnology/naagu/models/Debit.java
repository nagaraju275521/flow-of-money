package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Debit implements Serializable{
	
	private long id;
	private String debit_Date;
	private String debit_Name;
	private BigDecimal debit_Amount;
	private String debit_Source;
	private String debit_Description;
	private String user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDebit_Date() {
		return debit_Date;
	}
	public void setDebit_Date(String debit_Date) {
		this.debit_Date = debit_Date;
	}
	public String getDebit_Name() {
		return debit_Name;
	}
	public void setDebit_Name(String debit_Name) {
		this.debit_Name = debit_Name;
	}
	public BigDecimal getDebit_Amount() {
		return debit_Amount;
	}
	public void setDebit_Amount(BigDecimal debit_Amount) {
		this.debit_Amount = debit_Amount;
	}
	public String getDebit_Source() {
		return debit_Source;
	}
	public void setDebit_Source(String debit_Source) {
		this.debit_Source = debit_Source;
	}
	public String getDebit_Description() {
		return debit_Description;
	}
	public void setDebit_Description(String debit_Description) {
		this.debit_Description = debit_Description;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	
}
