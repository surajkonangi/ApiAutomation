package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class GetByIdApiWiremock {

	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	private static WireMockServer server = new WireMockServer(PORT);

	@BeforeClass
	public static void setup() {
		server.start();
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		mockResponse.withStatus(200).withHeader("Content-Type", "application/json");
		WireMock.configureFor(HOST, PORT); // http://localhost:8080
		WireMock.stubFor(WireMock.get("/api/example").willReturn(mockResponse));
	}

	@Test
	public void get() throws URISyntaxException {
		String keyvalue = "X-Api-Key:your_api_key_here";

		Response response = RestAssured.given().baseUri("http://localhost:8080").header("Authentication", keyvalue)
				.accept(ContentType.JSON).when().get("/api/employees/1").then().assertThat().statusCode(200).and()
				.body("employee_name", Matchers.equalTo("suraj")).log().all().extract().response();
		System.out.println(response.getBody().asString());
	}

	@AfterClass
	public static void teardown() {
		if (null != server && server.isRunning()) {
			server.shutdownServer();
		}
	}
}
