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
* cd to ./jaws-central/libs
```
 java -jar jaws-central-1.0.jar 
```
* Printing usage:
```
 java -jar jaws-central-1.0.jar --help
```