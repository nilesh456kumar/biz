package com.cap.biz.pojo;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="p_id")
	
	private  long pid;
	
	/*@Column(name="p_brand")
	private String brand;
	*/
	@Column(name="p_name")
	@NotNull
	@Size(min=2,max=10, message="Name should have atleast 2 characters")
	private String pname;
	
/*	@Column(name="p_price")
	private String price;
	
	@Column(name="p_mfg_Date")
	private Date mfgDate;
	
	@Column(name="p_exp_Date")
	private Date expDate;
	
	@Column(name="p_quantity_Avalible")
	private String quantityAvalible;
*/

	public Product() {
		super();
		
	}
	
	public Product(long pid, String pname) {
	
		this.pid = pid;
		this.pname = pname;
	}


	public long getPid() {
		return pid;
	}


	public void setPid(long pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}
/*
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getQuantityAvalible() {
		return quantityAvalible;
	}

	public void setQuantityAvalible(String quantityAvalible) {
		this.quantityAvalible = quantityAvalible;
	}
	
	*/

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + "]";
	}
	

}
