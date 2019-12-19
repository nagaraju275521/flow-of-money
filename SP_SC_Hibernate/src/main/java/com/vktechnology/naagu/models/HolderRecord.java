package com.vktechnology.naagu.models;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Created by HOME on 12/20/2015.
 */
@Entity
@Table(name = "holder_record")
public class HolderRecord {

    private long 	holder_Id;
    private String 	holder_Date;
    private String	holder_Name;
    private BigDecimal 	holder_Withdraw;
    private BigDecimal 	holder_Balance;
    private String 	holder_SourceName;
    private BigDecimal 	holder_SourceBalance;
    private String 	holder_Description;
    private String 	holder_User;

    @Id
    @GeneratedValue
    @Column(name="Holder_Id")
    public long getHolder_Id() {
        return holder_Id;
    }

    public void setHolder_Id(long holder_Id) {
        this.holder_Id = holder_Id;
    }

    @Column(name="Holder_Date")
	public String getHolder_Date() {
		return holder_Date;
	}

	public void setHolder_Date(String holder_Date) {
		this.holder_Date = holder_Date;
	}
	
	
	@Column(name="Holder_Name")
	public String getHolder_Name() {
		return holder_Name;
	}

	public void setHolder_Name(String holder_Name) {
		this.holder_Name = holder_Name;
	}

	@Column(name="Holder_Withdraw")
	public BigDecimal getHolder_Withdraw() {
		return holder_Withdraw;
	}

	public void setHolder_Withdraw(BigDecimal holder_Withdraw) {
		this.holder_Withdraw = holder_Withdraw;
	}

	@Column(name="Holder_Balance")
	public BigDecimal getHolder_Balance() {
		return holder_Balance;
	}

	public void setHolder_Balance(BigDecimal holder_Balance) {
		this.holder_Balance = holder_Balance;
	}

	@Column(name="Holder_SourceName")
	public String getHolder_SourceName() {
		return holder_SourceName;
	}

	public void setHolder_SourceName(String holder_SourceName) {
		this.holder_SourceName = holder_SourceName;
	}

	@Column(name="Holder_SourceBalance")
	public BigDecimal getHolder_SourceBalance() {
		return holder_SourceBalance;
	}

	public void setHolder_SourceBalance(BigDecimal holder_SourceBalance) {
		this.holder_SourceBalance = holder_SourceBalance;
	}

	@Column(name="Holder_Description")
	public String getHolder_Description() {
		return holder_Description;
	}

	public void setHolder_Description(String holder_Description) {
		this.holder_Description = holder_Description;
	}

	@Column(name="Holder_User")
	public String getHolder_User() {
		return holder_User;
	}

	public void setHolder_User(String holder_User) {
		this.holder_User = holder_User;
	}

        
}
