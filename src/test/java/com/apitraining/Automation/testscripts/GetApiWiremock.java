package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetApiWiremock {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeClass
    public static void setup() {
        server.start();
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200).withBody(
                "{\"message\": \"This is a mock response\", \"id\": 1, \"employee_name\": \"suraj\", \"employee_salary\": 35274, \"employee_age\": 30}")
                .withHeader("Content-Type", "application/json");

        WireMock.configureFor(HOST, PORT); // http://localhost:8080
        WireMock.stubFor(WireMock.get("/api/example").willReturn(mockResponse));
    }

    @Test
    public void get() throws URISyntaxException {
   Response res=     RestAssured.given().accept(ContentType.JSON).when().get(new URI("http://localhost:8080/api/example")).then()
                .assertThat().statusCode(200).and().body("employee_name", Matchers.equalTo("suraj")).extract().response();
        System.out.println(res.getBody().asString());
    }

    @AfterClass
    public static void teardown() {
        if (null != server && server.isRunning()) {
            server.shutdownServer();
        }
    }
}
