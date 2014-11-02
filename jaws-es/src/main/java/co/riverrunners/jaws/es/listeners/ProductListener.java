package co.riverrunners.jaws.es.listeners;

import co.riverrunners.jaws.bbhr.model.Product;

import java.util.List;

/**
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public interface ProductListener {


    public void handleProducts(List<Product> productList);

}
