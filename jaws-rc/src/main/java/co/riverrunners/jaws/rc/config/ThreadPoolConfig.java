package co.riverrunners.jaws.rc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring based thread pool for processing work. Values for minPoolSize and maxPoolSize
 * are set from application.properties via spring Value/Property.
 * minPoolSize defaults to 5 if jaws.rc.minpoolsize in application.properties is not set.
 * maxPoolSize defaults to 10 if jaws.rc.maxpoolsize in application.properties is not set.
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Configuration
public class ThreadPoolConfig {

    @Value("${jaws.rc.minpoolsize:5}")
    private String minPoolSize;
    @Value("${jaws.rc.maxpoolsize:10}")
    private String maxPoolSize;


    @Bean public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor  pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(Integer.valueOf(minPoolSize));
        pool.setMaxPoolSize(Integer.valueOf(maxPoolSize));
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;

    }

}
