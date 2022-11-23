package com.estock.company.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="stock_price")
public class StockPriceEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int stockId;
	
	@Column(name="stock_price", nullable = false)
	private Double stockPrice;
	
	@CreationTimestamp
	@Column(name="created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name="company_code", insertable = false, updatable = false, nullable = false)
	@Length(max=10)
	private String companyCode;

	@ManyToOne
	@JoinColumn(name = "company_code")
	@JsonIgnore
	private CompanyInfoEntity compInfoEntity;
	
	/**
	 * @return the stockId
	 */
	public int getStockId() {
		return stockId;
	}

	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the stockPrice
	 */
	public Double getStockPrice() {
		return stockPrice;
	}

	/**
	 * @param stockPrice the stockPrice to set
	 */
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public CompanyInfoEntity getCompInfoEntity() {
		return compInfoEntity;
	}
	
	public void setCompInfoEntity(CompanyInfoEntity compInfoEntity) {
		this.compInfoEntity = compInfoEntity;
	}

	@Override
	public String toString() {
		return "StockPrice [stockId=" + stockId + ", stockPrice=" + stockPrice
				+ ", createdDateTime=" + createdDateTime + ", companyCode" + companyCode + "]";
	}

}
