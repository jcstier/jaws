package co.riverrunners.jaws.es.repositories;

import co.riverrunners.jaws.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Data access object to product documents in ElasticSearch
 */
@Repository
public class ProductRepository {
    private Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private Client client = null;
    private Settings settings;
    private ObjectMapper jsonMapper;
    private static String PRODUCT_INDEX = "rr";
    private static String PRODUCT_TYPE = "products";
    private boolean useTransportClient = true;
    @Value("${es.hostname:localhost}")
    private String elasticSearchHost;
    @Value("${es.port:9300}")
    private int elasticSearchPort;

    @PostConstruct
    private void init() {
        logger.info("Initializing ProductRepository");
        jsonMapper = new ObjectMapper();

        if (useTransportClient) {
            settings = ImmutableSettings.settingsBuilder()
                    .put("client.transport.sniff", true)
                    .build();
            logger.info("Connecting to ElasticSearch on {}:{}", elasticSearchHost, elasticSearchPort);
            client = new TransportClient()
                    .addTransportAddress(new InetSocketTransportAddress(elasticSearchHost, elasticSearchPort));
        } else {
            // this creates an internal ElasticSearch and should only be used for testing.  useTransportClient
            // should only be set to false through unit tests.
            logger.info("Using embedded ElasticSearch.  This should only be used for testing");
            Node node = NodeBuilder.nodeBuilder().node();
            client = node.client();
        }

    }

    @PreDestroy
    private void destroy() {
        if (client != null) {
            client.close();
        }
    }

    /**
     * Saves a product to ElasticSearch
     *
     * @param product Product to save
     * @throws co.riverrunners.jaws.es.repositories.JawsRepositoryException
     */
    public void saveProduct(Product product) throws JawsRepositoryException {
        // Make sure we at least have an ASIN.
        if (product.getAsin() == null || product.getAsin().isEmpty()) {
            throw new JawsRepositoryException("Cannot persist product without ASIN. Set ASIN in product before saving.");
        }

        String json = "";
        try {
            json = jsonMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            logger.error("Error marshalling product to json", e);
            throw new JawsRepositoryException(e);
        }
        logger.debug("Saving product: {}",json);
        client.prepareIndex(PRODUCT_INDEX, PRODUCT_TYPE, product.getAsin())
                .setSource(json)
                .execute()
                .actionGet();
    }

    /**
     * Gets a product from ElasticSearch with the given ASIN
     *
     * @param asin ASIN of the product you wish to get.
     * @return Product that was found
     * @throws JawsRepositoryException
     */
    public Product getProduct(String asin) throws JawsRepositoryException {
        try {
            logger.debug("Getting product with ASIN {}", asin);
            GetResponse response = client.prepareGet(PRODUCT_INDEX, PRODUCT_TYPE, asin)
                    .execute()
                    .actionGet();
            String json = response.getSourceAsString();
            logger.debug("Got product: {}", json);
            Product product = jsonMapper.readValue(json, Product.class);
            return product;

        } catch (Exception e) {
            logger.error("Unknown error getting product with ASIN: {}", asin, e);
            throw new JawsRepositoryException(e);
        }
    }

}