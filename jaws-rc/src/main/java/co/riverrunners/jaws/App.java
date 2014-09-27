package co.riverrunners.jaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {


    public static void main(String[] args){

        ApplicationContext appContext = SpringApplication.run(App.class, args);



    }

}
