package co.riverrunners.jaws.central.services;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Gets work to be done and dispatches it over a jms queue
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Service
public class WorkDispatcher {
    private Logger logger = LoggerFactory.getLogger(WorkDispatcher.class);

    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${jaws.central.jms.work.queue:jaws.work}")
    private String workQueueDestination;

    private JmsTemplate jmsTemplate;

    @PostConstruct
    private void init() {
        logger.debug("Initializing WorkDispatcher");
        jmsTemplate = context.getBean(JmsTemplate.class);
        String brokerurl = ((ActiveMQConnectionFactory)jmsTemplate.getConnectionFactory()).getBrokerURL();
        logger.debug("ActiveMQ Broker URL: {}", brokerurl);
    }


    public void testSend(String someMessage){
        logger.debug("Sending message: {}", someMessage);

        jmsTemplate.send(workQueueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(someMessage);
            }
        });

    }

}
