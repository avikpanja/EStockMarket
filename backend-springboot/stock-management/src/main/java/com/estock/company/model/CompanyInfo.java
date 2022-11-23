package com.estock.company.model;

public class CompanyInfo {

	private String companyCode;
	private String companyName;
	private String ceo;
	private Double turnover;
	private String website;
	private String stockExchange;
	private Double stockPrice;
	
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the ceo
	 */
	public String getCeo() {
		return ceo;
	}
	/**
	 * @param ceo the ceo to set
	 */
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	/**
	 * @return the turnover
	 */
	public Double getTurnover() {
		return turnover;
	}
	/**
	 * @param turnover the turnover to set
	 */
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the stockExchange
	 */
	public String getStockExchange() {
		return stockExchange;
	}
	/**
	 * @param stockExchange the stockExchange to set
	 */
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	public Double getStockPrice() {
		return stockPrice;
	}
	
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	@Override
	public String toString() {
		return "CompanyInfo [companyCode=" + companyCode + ", companyName=" + companyName + ", ceo=" + ceo
				+ ", turnover=" + turnover + ", website=" + website + ", stockExchange=" + stockExchange + "]";
	}
	
}
