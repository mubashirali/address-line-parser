package com.addressline.parser;

import dto.AddressRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressLineAddressParserTest {

    @LocalServerPort
    private Integer localeServicePort;

    @Value("http://localhost/api/address")
    private String baseUrl;

    @BeforeEach
    void setup() {
        RestAssured.port = localeServicePort;
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void simpleAddressParsingTest() {
        String requestUrl = format("%s/parse", baseUrl);
        AddressRequestDTO request = new AddressRequestDTO("4 rue de la revolution");

        given().body(request).contentType(ContentType.JSON)
                .when().post(requestUrl).prettyPeek()
                .then().statusCode(HttpStatus.OK.value())
                .body("street", Matchers.equalTo("rue de la revolution"))
                .body("housenumber", Matchers.equalTo("4"));
    }

    @Test
    void complexAddressParsingTest() {
        String requestUrl = format("%s/parse", baseUrl);
        AddressRequestDTO request = new AddressRequestDTO("Auf der VogÄ, 23 b");

        given().body(request).contentType(ContentType.JSON)
                .when().post(requestUrl).prettyPeek()
                .then().statusCode(HttpStatus.OK.value())
                .body("street", Matchers.equalTo("Auf der VogÄ"))
                .body("housenumber", Matchers.equalTo("23 b"));
    }

    @Test
    void invalidAddressTest() {
        String requestUrl = format("%s/parse", baseUrl);
        AddressRequestDTO request = new AddressRequestDTO("+ Calle 39 1540");

        given().body(request).contentType(ContentType.JSON)
                .when().post(requestUrl).prettyPeek()
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
