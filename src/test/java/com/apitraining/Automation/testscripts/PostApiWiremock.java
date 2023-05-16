package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;

import com.apitraining.Automation.utils.ConstantPaths;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PostApiWiremock {

	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	private static WireMockServer server = new WireMockServer(PORT);
	public static JSONObject input;

	@BeforeTest
	public void beforeTest() throws Exception {
		JSONParser data = new JSONParser();
		FileReader jsondata = new FileReader(ConstantPaths.Json_postwiremockData);
		Object obj = data.parse(jsondata);
		input = (JSONObject) obj;
		RestAssured.given().body(input.toJSONString());
	}

	@BeforeClass
	public static void setup() {
		server.start();
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		mockResponse.withStatus(200).withHeader("Content-Type", "application/json");

		WireMock.configureFor(HOST, PORT); // http://localhost:8080
		WireMock.stubFor(WireMock.post("/api/employee/create").willReturn(mockResponse));
	}

	@Test
	public void post() throws URISyntaxException {
		Response res = RestAssured.given().baseUri("http://localhost:8080").body(input.toString())
				.accept(ContentType.JSON).when().post("/api/employee/create").then().assertThat().statusCode(200).and()
				.body("employee_name", Matchers.equalTo("suraj")).extract().response();
		System.out.println(res.getBody().asString());
	}

	@AfterClass
	public static void teardown() {
		if (null != server && server.isRunning()) {
			server.shutdownServer();
		}
	}
}
