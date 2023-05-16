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

public class DeleteApiWiremock {

	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	private static WireMockServer server = new WireMockServer(PORT);

	@BeforeClass
	public static void setup() {
		server.start();
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		mockResponse.withStatus(200).withHeader("Content-Type", "application/json");
		WireMock.configureFor(HOST, PORT); // http://localhost:8080
		WireMock.stubFor(WireMock.delete("/api/employee/delete/1").willReturn(mockResponse));
	}

	@Test
	public void delete() throws URISyntaxException {
		Response response = RestAssured.given().baseUri("http://localhost:8080").accept(ContentType.JSON).when()
				.delete("/api/employee/delete/1").then().assertThat().statusCode(200).and().log().all().extract()
				.response();
		System.out.println(response.getBody().asString());
	}

	@AfterClass
	public static void teardown() {
		if (null != server && server.isRunning()) {
			server.shutdownServer();
		}
	}
}
