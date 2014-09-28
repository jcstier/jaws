package co.riverrunners.jaws.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Product model - holds asin, and other info for a project.
 * @author Chris Stier <john.c.stier@gmail.com>
 */

public class Product {
    private String asin;
    private String msku;
    private String upc;
    private List<String> titles;
    private String brand;
    private String description;
    private String url;
    private String department;
    private Map<String,String> rankings;
    private Map<String,String> ratings;
    private Map<String,String> features;
    private Map<String,String> images;
    private Date created_at;
    private Date updated_at;

    public Product() {
    }

    public Product(String asin, String msku, String upc, List<String> titles, String brand, String description,
                   String url, String department, Map<String, String> rankings, Map<String, String> ratings,
                   Map<String, String> features, Map<String, String> images, Date created_at, Date updated_at) {
        this.asin = asin;
        this.msku = msku;
        this.upc = upc;
        this.titles = titles;
        this.brand = brand;
        this.description = description;
        this.url = url;
        this.department = department;
        this.rankings = rankings;
        this.ratings = ratings;
        this.features = features;
        this.images = images;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Map<String, String> getRankings() {
        return rankings;
    }

    public void setRankings(Map<String, String> rankings) {
        this.rankings = rankings;
    }

    public Map<String, String> getRatings() {
        return ratings;
    }

    public void setRatings(Map<String, String> ratings) {
        this.ratings = ratings;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
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

    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }
}
