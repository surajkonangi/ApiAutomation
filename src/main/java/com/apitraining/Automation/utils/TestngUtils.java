package com.apitraining.Automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestngUtils {

	private static final Logger logger = LogManager.getLogger(TestngUtils.class);
	public Response response;
	public Properties prop;
	public JSONObject input;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		prop = new Properties();
		FileInputStream path = new FileInputStream(ConstantPaths.Testdata_Properties);

		prop.load(path);
		RestAssured.baseURI = prop.getProperty("url");
		logger.info("Base Uri successful");

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		JSONParser data = new JSONParser();
		FileReader jsondata = new FileReader(ConstantPaths.Json_Data);
		Object obj = data.parse(jsondata);
		input = (JSONObject) obj;
		RestAssured.given().body(input.toJSONString());
		RestAssured.given().auth().basic(prop.getProperty("user"), prop.getProperty("password"));
	}

	public void apiCalls() {
		String request = ConstantPaths.request;
		if (request.equals("Post")) {
			response = RestAssured.given().contentType(prop.getProperty("content")).when()
					.post(prop.getProperty("postpath")).then().log().all().extract().response();
			System.out.println(response);
		} else if (request.equals("Get")) {
			response = RestAssured.given().when().get(prop.getProperty("getpath")).then().log().all().extract()
					.response();
			System.out.println(response);
		} else if (request.equals("Put")) {
			response = RestAssured.given().when().put("putpath").then().log().all().extract().response();
			System.out.println(response);
		} else if (request.equals("Patch")) {
			response = RestAssured.given().when().patch("patchpath").then().log().all().extract().response();
			System.out.println(response);
		} else {
			response = RestAssured.given().when().delete("deletepath").then().log().all().extract().response();
			System.out.println(response);
		}
	}

	@AfterTest
	public void afterTest() {
		System.out.println("status line is" + response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Validated Success");
		System.out.println("The body is " + response.getBody().asString());
		System.out.println("the status time is " + response.getTime());
		System.out.println("The status code is " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Suite executed successful");
	}

}
