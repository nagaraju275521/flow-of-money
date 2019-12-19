package com.vktechnology.naagu.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by HOME on 11/22/2015.
 */
public class Expenses implements Serializable{

    private long id;
    private String date;
    private String unit_Type;
    private String unit_Name;
    private String unit_Tag;
    private BigDecimal unit_price;
    private BigDecimal amount;
    private String quantity;
    private String source_Name;
    private BigDecimal source_bal;
    private String description;
    private String sold_company;
    private String area;
    private String whome_to;
    private String user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit_Type() {
        return unit_Type;
    }

    public void setUnit_Type(String unit_Type) {
        this.unit_Type = unit_Type;
    }

    public String getUnit_Name() {
        return unit_Name;
    }

    public void setUnit_Name(String unit_Name) {
        this.unit_Name = unit_Name;
    }

    public String getUnit_Tag() {
		return unit_Tag;
	}

	public void setUnit_Tag(String unit_Tag) {
		this.unit_Tag = unit_Tag;
	}

	public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }   
    

    public BigDecimal getSource_bal() {
		return source_bal;
	}

	public void setSource_bal(BigDecimal source_bal) {
		this.source_bal = source_bal;
	}

	public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSource_Name() {
        return source_Name;
    }

    public void setSource_Name(String source_Name) {
        this.source_Name = source_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSold_company() {
        return sold_company;
    }

    public void setSold_company(String sold_company) {
        this.sold_company = sold_company;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWhome_to() {
        return whome_to;
    }

    public void setWhome_to(String whome_to) {
        this.whome_to = whome_to;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
