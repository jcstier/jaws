package co.riverrunners.jaws.model;

import java.io.Serializable;

/**
 * Class to represent a line item in the BBHR (Buy Box Hold Rate)
 * report.
 * 
 * @author David Nelson <madhacker@tangouniform.org>
 *
 */
public class BBHRLineItem implements Serializable {

	private String parentASIN;
	private String childASIN;
	private String title;
	private String sku;
	private String sessions;
	private double sessionPercentage;
	private int pageViews;
	private double pageViewsPercentage;
	private double buyBoxPercentage;
	private int unitsOrdered;
	private double unitSessionPercentage;
	private double grossProductSales;
	private int ordersPlaced;

	public BBHRLineItem() {

	}

	public BBHRLineItem(String parentASIN, String childASIN, String title,
			String sku, String sessions, double sessionPercentage,
			int pageViews, double pageViewsPercentage, double buyBoxPercentage,
			int unitsOrdered, double unitSessionPercentage,
			double grossProductSales, int ordersPlaced) {
		
		this.parentASIN = parentASIN;
		this.childASIN = childASIN;
		this.title = title;
		this.sku = sku;
		this.sessions = sessions;
		this.sessionPercentage = sessionPercentage;
		this.pageViews = pageViews;
		this.pageViewsPercentage = pageViewsPercentage;
		this.buyBoxPercentage = buyBoxPercentage;
		this.unitsOrdered = unitsOrdered;
		this.unitSessionPercentage = unitSessionPercentage;
		this.grossProductSales = grossProductSales;
		this.ordersPlaced = ordersPlaced;
	}

	public void setParentASIN(String parentASIN) {
		this.parentASIN = parentASIN;
	}

	public void setChildASIN(String childASIN) {
		this.childASIN = childASIN;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public void setSessionPercentage(double sessionPercentage) {
		this.sessionPercentage = sessionPercentage;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	public void setPageViewsPercentage(double pageViewsPercentage) {
		this.pageViewsPercentage = pageViewsPercentage;
	}

	public void setBuyBoxPercentage(double buyBoxPercentage) {
		this.buyBoxPercentage = buyBoxPercentage;
	}

	public void setUnitsOrdered(int unitsOrdered) {
		this.unitsOrdered = unitsOrdered;
	}

	public void setUnitSessionPercentage(double unitSessionPercentage) {
		this.unitSessionPercentage = unitSessionPercentage;
	}

	public void setGrossProductSales(double grossProductSales) {
		this.grossProductSales = grossProductSales;
	}

	public void setOrdersPlaced(int ordersPlaced) {
		this.ordersPlaced = ordersPlaced;
	}

	public String getParentASIN() {
		return parentASIN;
	}

	public String getChildASIN() {
		return childASIN;
	}

	public String getTitle() {
		return title;
	}

	public String getSku() {
		return sku;
	}

	public String getSessions() {
		return sessions;
	}

	public double getSessionPercentage() {
		return sessionPercentage;
	}

	public int getPageViews() {
		return pageViews;
	}

	public double getPageViewsPercentage() {
		return pageViewsPercentage;
	}

	public double getBuyBoxPercentage() {
		return buyBoxPercentage;
	}

	public int getUnitsOrdered() {
		return unitsOrdered;
	}

	public double getUnitSessionPercentage() {
		return unitSessionPercentage;
	}

	public double getGrossProductSales() {
		return grossProductSales;
	}

	public int getOrdersPlaced() {
		return ordersPlaced;
	}
}
