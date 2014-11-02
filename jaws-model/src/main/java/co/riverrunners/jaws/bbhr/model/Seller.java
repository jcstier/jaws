package co.riverrunners.jaws.bbhr.model;

import java.io.Serializable;

/**
 * Holds a seller's information
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class Seller implements Serializable {

    private String name;

    private double positivePercentage;


    public Seller() {
    }

    public Seller(String name, double positivePercentage) {
        this.name = name;
        this.positivePercentage = positivePercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPositivePercentage() {
        return positivePercentage;
    }

    public void setPositivePercentage(double positivePercentage) {
        this.positivePercentage = positivePercentage;
    }
}

