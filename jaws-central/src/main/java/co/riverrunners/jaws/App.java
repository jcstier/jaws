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
        if(args.length>0 && !args[0].isEmpty()){
            if(args[0].equals("--help")){
                printUsage();
                return;
            }
        }

        SpringApplication.run(App.class, args);
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
        System.out.println("\tjava -jar jaws-central.jar --server.port=8080 --es.hostname=192.168.1.33 --es.port=9300");
    }

}


