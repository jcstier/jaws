package co.riverrunners.jaws.es.repositories;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
class ProductRepository {
    private Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private Client client = null;
    private Settings settings;

    @Value("${es.hostname:localhost}")
    private String elasticSearchHost;
    @Value("${es.port:9300}")
    private int elasticSearchPort;

    @PostConstruct
    private void init() {
        logger.info("Connecting to ElasticSearch on {}:{}",elasticSearchHost,elasticSearchPort);
        settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.sniff", true)
                .build();
        client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(elasticSearchHost, elasticSearchPort));

    }

    @PreDestroy
    private void destroy() {
        if (client != null) {
            client.close();
        }
    }

}