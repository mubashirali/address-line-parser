# AddressLine Parser Service

Restful API that Parse given address.


    Write a simple program that does the task for the most simple cases, e.g.
        "Winterallee 3" -> {"street": "Winterallee", "housenumber": "3"}
        "Musterstrasse 45" -> {"street": "Musterstrasse", "housenumber": "45"}
        "Blaufeldweg 123B" -> {"street": "Blaufeldweg", "housenumber": "123B"}

    Consider more complicated cases
        "Am Bächle 23" -> {"street": "Am Bächle", "housenumber": "23"}
        "Auf der Vogelwiese 23 b" -> {"street": "Auf der Vogelwiese", "housenumber": "23 b"}

    Consider other countries (complex cases)
        "4, rue de la revolution" -> {"street": "rue de la revolution", "housenumber": "4"}
        "200 Broadway Av" -> {"street": "Broadway Av", "housenumber": "200"}
        "Calle Aduana, 29" -> {"street": "Calle Aduana", "housenumber": "29"}
        "Calle 39 No 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}

## Built with
```
JDK 1.8
Spring boot 2.3.1
maven
```

## Instructions:

##### Compile with command 
``` 
mvn clean install
```
##### Run Tests with:
```
mvn clean test
```
##### Run with command: 
```
Default Port: 8080
mvn spring-boot:run
```


#### POST Request:
```
 http://localhost:8080/api/address/parse
```
#### Request Body:
```
{ 
"address": "Am Bächle 23" 
}
```

#### Response:
```
{
  "street": "Am Bächle",
  "housenumber": "23"
}

```
