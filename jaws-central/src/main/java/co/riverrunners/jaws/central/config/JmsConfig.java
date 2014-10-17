package co.riverrunners.jaws.central.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import javax.jms.ConnectionFactory;

/**
 * Configures a JMS listener.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */

/*
    This configuration is disabled.  No need for central to consume messages at this point.
 */

//@Configuration
public class JmsConfig {
    private Logger logger = LoggerFactory.getLogger(JmsConfig.class);

//    @Value("${jaws.central.jms.work.queue:jaws.work}")
    private String workQueueDestination;


    public class Receiver{
        public void receiveMessage(String msg){
            logger.debug("Got message {}", msg);

        }
    }

//    @Bean
    Receiver receiver(){
        return new Receiver();
    }


//    @Bean
    MessageListenerAdapter adapter(Receiver receiver) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }


//    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter messageListenerAdapter,
                                             ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListenerAdapter);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(workQueueDestination);
        return container;
    }




}
