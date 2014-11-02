package co.riverrunners.jaws.test.es;

import co.riverrunners.jaws.es.listeners.ProductListener;
import co.riverrunners.jaws.es.repositories.JawsRepositoryException;
import co.riverrunners.jaws.es.repositories.ProductRepository;
import co.riverrunners.jaws.bbhr.model.Product;
import co.riverrunners.jaws.bbhr.model.Seller;
import co.riverrunners.jaws.bbhr.model.SellerProduct;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test operations from {@link co.riverrunners.jaws.es.repositories.ProductRepository}
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class ProductRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);
    private ProductRepository repo = null;
    private Product product = null;

    //
    // Since we don't have a full container we can't inject the repo
    // So we need to call the postconstruct ourselves through java reflection.
    // This will allow us to init the repo like it would be from spring.
    private void initRepo(ProductRepository repo) {
        try {
            // Tell repo to use embedded ElasticSearch and not the real one
            Field useTransportClient = repo.getClass().getDeclaredField("useTransportClient");
            useTransportClient.setAccessible(true);
            useTransportClient.set(repo, false);

            // set scrolling timeout
            Field scrollTimeout = repo.getClass().getDeclaredField("scrollTimeout");
            scrollTimeout.setAccessible(true);
            scrollTimeout.set(repo, 6000);

            // calls the init method in ProductRepository
            Method method = repo.getClass().getDeclaredMethod("init");
            method.setAccessible(true);
            method.invoke(repo);
        } catch (NoSuchMethodException e) {
            logger.error("Cannot find method init", e);
            Assert.fail();
        } catch (InvocationTargetException e) {
            logger.error("Cannot find method init", e);
            Assert.fail();
        } catch (IllegalAccessException e) {
            logger.error("Cannot access init", e);
            Assert.fail();
        } catch (NoSuchFieldException e) {
            logger.error("Cannot find field", e);
            Assert.fail();
        }
    }

    @Before
    public void initTest() {
        repo = new ProductRepository();
        initRepo(repo);
        product = new Product();
        product.setAsin("B00K57ZIPU");
        product.setBrand("HiLine Coffee");
        product.setDepartment("Grocery & Gourmet Food");
        product.setDescription("Our Original Mild Roast Nespresso compatible coffee capsules offer an easy drinking " +
                "espresso for any time of the day and are the perfect introduction to our coffee range. " +
                "With a full body, this delicious blend has a softly rounded character with an occasional spark of " +
                "light acidity. Constructed with the finest beans from Colombia, Costa Rica and Kenya, its light roast " +
                "yields fruity flavors with notes of cinnamon, citrus and blackberry.");
        product.setName("HiLine Coffee Nespresso Compatible Capsule United Nations Decaf - 60 Count");
        product.setUpc("616913837420");
        product.setUrl("/gp/product/B00K57ZIPU");
        product.setMsku("NOT_SURE_WHERE_TO_GET_THIS_VALUE");
        List<SellerProduct> sellers = new ArrayList<>();
        sellers.add(new SellerProduct("HiLine Coffee Nespresso Compatible Capsule United Nations Decaf - 60 Count",
                new Seller("HiLine Coffee", 76.0),
                "new",
                "prime",
                4.99,
                3.00,
                7.99,
                15,
                "/some/img/url.png"));
        product.setSellerProducts(sellers);
        product.setCreated_at(new Date());
        product.setUpdated_at(new Date());
    }

    @Test
    public void testSaveProduct() {
        logger.info("Starting testSaveProduct()");
        try {
            repo.saveProduct(product);
            Product savedProduct = repo.getProduct(product.getAsin());
            logger.info("testSaveProduct() completed successfully.");
        } catch (JawsRepositoryException e) {
            logger.error("Error saving product to repo", e);
            Assert.fail();
        }

    }

    @Test
    public void testGetProducts() {
        logger.info("Starting testGetProducts()");
        try {
            repo.saveProduct(product); // TODO: need to add much more than just one product for this test.
            repo.getProducts(100, new ProductListener() {
                @Override
                public void handleProducts(List<Product> productList) {
                    for (Product product : productList) {
                        logger.info("Got product: {}", product.getAsin());
                    }
                }
            });
            logger.info("testGetProducts() completed successfully");
        } catch (JawsRepositoryException e) {
            logger.error("Error getting products", e);
            Assert.fail();
        }

    }

}
