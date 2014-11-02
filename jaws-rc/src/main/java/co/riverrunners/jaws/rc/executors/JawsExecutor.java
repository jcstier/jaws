package co.riverrunners.jaws.rc.executors;

import co.riverrunners.jaws.bbhr.model.dispatcher.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
    private WorkQueue workQueue;

    @PostConstruct
    private void init(){
        logger.debug("Starting JawsExecutor");

    }

    /*
     * Process the work queue. Pulls work from the the queue and executes it
     * with the executor.  The fixedDelay will start at 5 seconds... This is an
     * easy way to get a one shot timer call to work.  The @Scheduled will go
     * off creating process loop to grab items off the queue and put them in the
     * executor.
     */
    @Scheduled(fixedDelay = 5000)
    private void process(){
        while(true){
            logger.debug("JawsExecutor: running!");
            if(executor.getActiveCount() < executor.getMaxPoolSize()) {
                Worker worker = workQueue.popWorker();
                if (worker != null) {
                    worker.postRun();
                    executor.execute(worker);
                }
            }
        }

    }

}
