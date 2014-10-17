package co.riverrunners.jaws.rc.executors;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Work executor for processing work from jms.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Service
public class JawsExecutor {
    private Logger logger = LoggerFactory.getLogger(JawsExecutor.class);

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private ConfigurableApplicationContext context;

    private JmsTemplate jmsTemplate;

    @PostConstruct
    private void init(){
        logger.debug("Starting JawsExecutor");
        logger.debug("Initializing WorkDispatcher");
        jmsTemplate = context.getBean(JmsTemplate.class);
        String brokerurl = ((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL();
        logger.debug("ActiveMQ Broker URL: {}", brokerurl);
    }


    public void receiveMessage(String message){
        logger.debug("Got Message: {}", message);
    }


}
