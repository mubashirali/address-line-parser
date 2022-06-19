# AddressLine Parser Service

Restful API that Parse given address.


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
