package co.riverrunners.jaws.rc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application entry point for jaws-rc.  Jaws-rc will be a distributed worker application
 * that receives its work from jaws-central via jms queue.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {


    public static void main(String[] args){
        if(args.length == 1 && !args[0].isEmpty()){
            if(args[0].equals("--help")){
                printUsage();
                return;
            }
        }

        ApplicationContext appContext = SpringApplication.run(App.class, args);
    }

    /**
     * Prints program usages to STDOUT-
     */
    public static void printUsage(){
        System.out.println("Usage:");
        System.out.println("--server.port={some port number}");
        System.out.println("\tDefault port number is 12398. This is the port that the jaws-central web server will run on.");
        System.out.println("--es.hostname={some host name}");
        System.out.println("\tDefault ElasticSearch hostname is localhost.  " +
                "This should be the hostname or IP where ElasticSearch is running");
        System.out.println("--es.port={some port number}");
        System.out.println("\tDefault ElasticSearch port number is 9300.");
        System.out.println("Example that overrides all defaults:");
        System.out.println("\tjava -jar jaws-rc.jar --server.port=8080 --es.hostname=192.168.1.33 --es.port=9300");
    }

}
