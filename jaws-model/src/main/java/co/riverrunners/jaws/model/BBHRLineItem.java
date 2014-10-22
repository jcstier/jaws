package co.riverrunners.jaws.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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
	
	public static double parseDouble(String s) throws ParseException{
		return NumberFormat.getNumberInstance(java.util.Locale.US).parse(s).doubleValue();
	}
	
	public static int parseInt(String s) throws ParseException{
		return NumberFormat.getNumberInstance(java.util.Locale.US).parse(s).intValue();
	}
	
	public static double parsePercent(String s) throws ParseException{
		return NumberFormat.getPercentInstance(java.util.Locale.US).parse(s).doubleValue();
	}
	
	public static double parseDollars(String s) throws ParseException{
		// the currency instance does not work correctly for US Dollars in Java - go figure.
		return parseDouble(s.replace("$", ""));
	}
	
	public BBHRLineItem(String[] data) throws ParseException {
		this.parentASIN = data[0];
		this.childASIN = data[1];
		this.title = data[2];
		this.sku = data[3];
		this.sessions = data[4];
		this.sessionPercentage = parsePercent(data[5]);
		this.pageViews = parseInt(data[6]);
		this.pageViewsPercentage = parsePercent(data[7]);
		this.buyBoxPercentage = parsePercent(data[8]);
		this.unitsOrdered = parseInt(data[9]);
		this.unitSessionPercentage = parsePercent(data[10]);
		this.grossProductSales = parseDollars(data[11]);
		this.ordersPlaced = parseInt(data[12]);		
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
	
	public String toString() {
		return "Parent ASIN: " + parentASIN + 
		", Child ASIN: " + childASIN + 
		", Title: " + title +
		", SKU: " + sku +
		", Sessions: " + sessions +
		", Session Percentage: " + NumberFormat.getPercentInstance().format(sessionPercentage) +
		", Page Views: " + pageViews +
		", Page Views Percentage: " + NumberFormat.getPercentInstance().format(pageViewsPercentage) +
		", Buy Box Percentage: " + NumberFormat.getPercentInstance().format(buyBoxPercentage) +
		", Units Ordered: " + unitsOrdered +
		", Unit Session Percentage: " + NumberFormat.getPercentInstance().format(unitSessionPercentage) +
		", Gross Product Sales: " + NumberFormat.getCurrencyInstance(Locale.US).format(grossProductSales) +
		", Orders Placed: " + ordersPlaced;
	}
}
