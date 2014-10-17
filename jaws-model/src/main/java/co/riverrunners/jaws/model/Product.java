package co.riverrunners.jaws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Product model - holds asin, and other info for a project.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */

public class Product implements Serializable{
    private String asin;
    private String msku;
    private String upc;
    private String brand;
    private String name;
    private String description;
    private String url;
    private String department;
    private List<SellerProduct> sellerProducts;
    private Date created_at;
    private Date updated_at;

    public Product() {
    }


    public Product(String asin, String msku, String upc, String brand, String name, String description, String url,
                   String department, List<SellerProduct> sellerProducts, Date created_at, Date updated_at) {
        this.asin = asin;
        this.msku = msku;
        this.upc = upc;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.url = url;
        this.department = department;
        this.sellerProducts = sellerProducts;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SellerProduct> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(List<SellerProduct> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
