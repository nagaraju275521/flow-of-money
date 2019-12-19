package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Credit implements Serializable {

	private long id;
	private String credit_Date;
	private String credit_Name;
	private BigDecimal credit_Amount;
	private BigDecimal credit_C_Amount;
	private String Credit_Description;
	private String user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCredit_Date() {
		return credit_Date;
	}

	public void setCredit_Date(String credit_Date) {
		this.credit_Date = credit_Date;
	}

	public String getCredit_Name() {
		return credit_Name;
	}

	public void setCredit_Name(String credit_Name) {
		this.credit_Name = credit_Name;
	}

	public BigDecimal getCredit_Amount() {
		return credit_Amount;
	}

	public void setCredit_Amount(BigDecimal credit_Amount) {
		this.credit_Amount = credit_Amount;
	}

	public BigDecimal getCredit_C_Amount() {
		return credit_C_Amount;
	}

	public void setCredit_C_Amount(BigDecimal credit_C_Amount) {
		this.credit_C_Amount = credit_C_Amount;
	}

	public String getCredit_Description() {
		return Credit_Description;
	}

	public void setCredit_Description(String credit_Description) {
		Credit_Description = credit_Description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
