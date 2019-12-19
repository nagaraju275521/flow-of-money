package com.vktechnology.naagu.models;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Created by HOME on 12/20/2015.
 */

@Entity
@Table(name = "moneyholder")
public class Holder {

    private long holder_Id;
    private String holder_Date;
    private String holder_Type;
    private String holder_Name;
    private BigDecimal holder_Amount;
    private String holder_Description;
    private String holder_Source;
    private String holder_User;

    @Id
    @GeneratedValue
    @Column(name="Holder_Id")
    public long getHolder_Id() {
        return holder_Id;
    }

    public void setHolder_Id(long holder_Id) {
        this.holder_Id = holder_Id;
    }

    @Column(name = "Holder_Date")
    public String getHolder_Date() {
        return holder_Date;
    }

    public void setHolder_Date(String holder_Date) {
        this.holder_Date = holder_Date;
    }

    @Column(name = "Holder_Type")
    public String getHolder_Type() {
        return holder_Type;
    }

    public void setHolder_Type(String holder_Type) {
        this.holder_Type = holder_Type;
    }

    @Column(name = "Holder_Name")
    public String getHolder_Name() {
        return holder_Name;
    }

    public void setHolder_Name(String holder_Name) {
        this.holder_Name = holder_Name;
    }

    @Column(name = "Holder_Amount")
    public BigDecimal getHolder_Amount() {
        return holder_Amount;
    }

    public void setHolder_Amount(BigDecimal holder_Amount) {
        this.holder_Amount = holder_Amount;
    }

    @Column(name = "Holder_Description")
    public String getHolder_Description() {
        return holder_Description;
    }

    public void setHolder_Description(String holder_Description) {
        this.holder_Description = holder_Description;
    }

    @Column(name = "Holder_Source")
    public String getHolder_Source() {
        return holder_Source;
    }

    public void setHolder_Source(String holder_Source) {
        this.holder_Source = holder_Source;
    }

    @Column(name = "Holder_User")
    public String getHolder_User() {
        return holder_User;
    }

    public void setHolder_User(String holder_User) {
        this.holder_User = holder_User;
    }
}
