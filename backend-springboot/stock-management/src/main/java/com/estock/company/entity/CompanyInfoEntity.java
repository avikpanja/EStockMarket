package com.estock.company.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="company_info")
public class CompanyInfoEntity implements Serializable {
	
	@Id
	@Column(name="code")
	@Length(max=10)
	private String companyCode;
	
	@Column(name="name", nullable = false)
	private String companyName;
	
	@Column(name="ceo", nullable = false)
	private String ceo;
	
	@Column(name="turnover", nullable = false)
	private Integer turnover;
	
	@Column(name="website", nullable = false)
	private String website;
	
	@Column(name="stock_exchange", nullable = false)
	private String stockExchange;
	
	@Column(name="stock_price")
	private Double stockPrice;
	
	@OneToMany(mappedBy = "compInfoEntity", cascade = CascadeType.ALL)
	private List<StockPriceEntity> stockPriceHistory;
	
	public CompanyInfoEntity() {}
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public Integer getTurnover() {
		return turnover;
	}
	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public List<StockPriceEntity> getStockPriceHistory() {
		return stockPriceHistory;
	}

	public void setStockPriceHistory(List<StockPriceEntity> stockPriceHistory) {
		this.stockPriceHistory = stockPriceHistory;
	}

	public Double getStockPrice() {
		return stockPrice;
	}
	
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Override
	public String toString() {
		return "CompanyInfoEntity [companyCode=" + companyCode + ", companyName=" + companyName + ", ceo=" + ceo
				+ ", turnover=" + turnover + ", website=" + website + ", stockExchange=" + stockExchange
				+ ", stockPrice=" + stockPrice + ", stockPriceHistory=" + stockPriceHistory + "]";
	}
	
}
