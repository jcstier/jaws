package co.riverrunners.jaws.model;

import java.io.Serializable;

/**
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class SellerProduct implements Serializable {

    private String productName;
    private Seller seller;
    private String condition;
    private String delivery;
    private double price;
    private double shippingPrice;
    private double totalPrice;
    private int inventory;
    private String imageUrl;

    public SellerProduct() {
    }

    public SellerProduct(String productName, Seller seller, String condition, String delivery, double price,
                         double shippingPrice, double totalPrice, int inventory, String imageUrl) {
        this.productName = productName;
        this.seller = seller;
        this.condition = condition;
        this.delivery = delivery;
        this.price = price;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
        this.inventory = inventory;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
