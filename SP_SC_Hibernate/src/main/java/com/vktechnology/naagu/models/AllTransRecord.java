package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class AllTransRecord implements Serializable{

    private long 	all_Id;
    private String 	all_Date;
    private String	all_Perticuler;
    private BigDecimal 	all_Deposite;
    private BigDecimal 	all_DBalance;
    private String 	all_SourceName;
    private BigDecimal 	all_SourceBal;
    private String 	all_Description;
    private String 	all_User;
    
	public long getAll_Id() {
		return all_Id;
	}
	public void setAll_Id(long all_Id) {
		this.all_Id = all_Id;
	}
	public String getAll_Date() {
		return all_Date;
	}
	public void setAll_Date(String all_Date) {
		this.all_Date = all_Date;
	}
	public String getAll_Perticuler() {
		return all_Perticuler;
	}
	public void setAll_Perticuler(String all_Perticuler) {
		this.all_Perticuler = all_Perticuler;
	}
	public BigDecimal getAll_Deposite() {
		return all_Deposite;
	}
	public void setAll_Deposite(BigDecimal all_Deposite) {
		this.all_Deposite = all_Deposite;
	}
	public BigDecimal getAll_DBalance() {
		return all_DBalance;
	}
	public void setAll_DBalance(BigDecimal all_DBalance) {
		this.all_DBalance = all_DBalance;
	}
	public String getAll_SourceName() {
		return all_SourceName;
	}
	public void setAll_SourceName(String all_SourceName) {
		this.all_SourceName = all_SourceName;
	}
	public BigDecimal getAll_SourceBal() {
		return all_SourceBal;
	}
	public void setAll_SourceBal(BigDecimal all_SourceBal) {
		this.all_SourceBal = all_SourceBal;
	}
	public String getAll_Description() {
		return all_Description;
	}
	public void setAll_Description(String all_Description) {
		this.all_Description = all_Description;
	}
	public String getAll_User() {
		return all_User;
	}
	public void setAll_User(String all_User) {
		this.all_User = all_User;
	}
    
    
    
    
}
