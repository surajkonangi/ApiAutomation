package com.apitraining.Automation.Wiremock.testscritps;

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
import java.util.HashMap;
import java.util.Map;

import com.apitraining.Automation.utils.ConstantPaths;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class PutApiWiremockBasicauth {

	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	private static WireMockServer server = new WireMockServer(PORT);
	public static JSONObject input;

	@BeforeTest
	public void beforeTest() throws Exception {
		JSONParser data = new JSONParser();
		FileReader jsondata = new FileReader(ConstantPaths.Json_putwiremockData);
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
		WireMock.stubFor(WireMock.put("/api/employee/update").willReturn(mockResponse));
	}

	@Test
	public void put() throws URISyntaxException {
		String hawkauthid = "surajkonangi";
		String hawkauthkey = "HBDJHV723YGDYWGED6723";
		String Algorithm = "sha256";

		Map<String, String> header = new HashMap<String, String>();
		String[] headers = { hawkauthid, hawkauthkey, Algorithm };

		for (String headerValue : headers) {
			header.put("Authorization", headerValue);

		}

		Response response = RestAssured.given().baseUri("http://localhost:8080").header("Content-Type", "application/json").headers(header).body(input.toString())
				.accept(ContentType.JSON).when().put("/api/employee/update").then().assertThat().statusCode(200).and()
				.body("data.employee_name", Matchers.equalTo("suraj")).extract().response();
		System.out.println(response.getBody().asString());
	}

	@AfterClass
	public static void teardown() {
		if (null != server && server.isRunning()) {
			server.shutdownServer();
		}
	}
}
