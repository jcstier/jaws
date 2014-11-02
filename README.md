# JAWS #
Just Another Web Scraper


### Projects ###
* jaws-central - The main work dispatcher to jaws-rc
* jaws-rc - jaws remote client.  The distributed app that will do the actual web scraping.
* jaws-es - jaws ElasticSearch module. This interfaces to ElasticSearch for CRUD operations.
* jaws-model - model classes for sending data between the remote client, central and ElasticSearch.

### Building JAWS ####
* cd to jaws root dir
```
gradle build
```



### Running JAWS ###
#### Running central ####
* Note: Prior to running RC and Central make sure ActiveMQ and ElasticSearch are running.
* cd to ./jaws-central/libs
```
 java -jar jaws-central-1.0.jar 
```
* Printing usage:
```
 java -jar jaws-central-1.0.jar --help
```

### Setting up ElasticSearch and ActiveMQ ###
* Download ElasticSearch at http://www.elasticsearch.org/overview/elkdownloads/
* Download ActiveMQ at http://activemq.apache.org/download.html
* Extract the archives somewhere on your hard drive.
* Run ElasticSearch by cd'ing into ElasticSearch_Root/bin and run ./elasticsearch
* No configuration is need for ElasticSeach... just run it.
* Run ActiveMQ by cd'ing into ActiveMQ_Root/bin and run  ./activemq start
* RC and Central will automatically look for these services on localhost. 
 