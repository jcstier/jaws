package co.riverrunners.jaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application entry point for jaws-central.  jaws-central will be
 * dispatching work through a jms queue to jaws-rc's.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
