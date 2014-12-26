package co.riverrunners.jaws.rc.config;

import co.riverrunners.jaws.rc.executors.JawsExecutor;
import co.riverrunners.jaws.rc.executors.WorkQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import javax.jms.ConnectionFactory;

/**
 * Spring JMS configuration. Configures the JMS listener to the
 * {@link co.riverrunners.jaws.rc.executors.JawsExecutor}
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Configuration
public class JmsConfig {

    /**
     * Loaded from application.properties
     */
    @Value("${jaws.central.jms.work.queue:jaws.work}")
    private String workQueueDestination;

    /*
        NOTE: This only connects to one queue. Haven't figured out how to connect
        to multiple queues or setup multiple listeners yet. I think we just need
        to implement a JmsConfig for each destination?  Will have to test.
     */


    /**
     * Sets the message listener.  {@link co.riverrunners.jaws.rc.executors.WorkQueue} is the JMS
     * listener.  Spring will pass the WorkQueue instance into this method where we tell it what
     * method to use as a listener callback.
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter adapter(WorkQueue receiver) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");

        return messageListener;
    }

    /**
     * This sets the queue name.  Spring also calls this with the the proper instances.
     * @param messageListenerAdapter
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer container(MessageListenerAdapter messageListenerAdapter,
                                             ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListenerAdapter);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(workQueueDestination);
        return container;
    }


}
