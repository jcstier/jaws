package co.riverrunners.jaws.rc.executors;

import co.riverrunners.jaws.bbhr.model.dispatcher.Worker;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Service
public class WorkQueue {
    private Logger logger = LoggerFactory.getLogger(WorkQueue.class);
    private ConcurrentLinkedDeque<Worker> workQueue;
    private JmsTemplate jmsTemplate;
    @Autowired
    private ConfigurableApplicationContext context;


    @PostConstruct
    private void init() {
        workQueue = new ConcurrentLinkedDeque<>();
        jmsTemplate = context.getBean(JmsTemplate.class);
        String brokerUrl = ((ActiveMQConnectionFactory) jmsTemplate.getConnectionFactory()).getBrokerURL();
        logger.debug("WorkQueue connected to ActiveMQ Broker URL: {}", brokerUrl);
    }

    /**
     * Receives a jms object from the broker and places it on the queue.
     *
     * @param message
     */
    public void receiveMessage(ObjectMessage message) {
        try {
            Object msg = (Object) message.getObject();
            if (msg instanceof Worker) {
                workQueue.add((Worker) msg);
            } else {
                logger.error("JMS message cannot be cast to Worker.  Message will not be processed.");
            }
        } catch (JMSException e) {
            logger.error("Error getting worker message {}", e);
        }

    }

    /**
     * Pop a {@link co.riverrunners.jaws.bbhr.model.dispatcher.Worker} from the queue.
     *
     * @return The next work worker or null if queue is empty
     */
    public Worker popWorker() {
        if (!workQueue.isEmpty()) {
            return workQueue.poll();
        } else {
            return null;
        }
    }

}
