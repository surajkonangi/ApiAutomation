package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class ParentClass {
	public Response response;
	public Properties prop;
	public JSONObject input;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		prop = new Properties();
		FileInputStream path = new FileInputStream(
				"C:\\Users\\suraj.konangi\\eclipse-workspace\\Automation\\src\\main\\resources\\Testdata.properties");

		prop.load(path);
		RestAssured.baseURI = prop.getProperty("url");

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		JSONParser data = new JSONParser();
		FileReader jsondata = new FileReader(
				"C:\\Users\\suraj.konangi\\eclipse-workspace\\Automation\\src\\main\\resources\\data.json");
		Object obj = data.parse(jsondata);
		input = (JSONObject) obj;
		input.get("name");
		input.get("salary");
		input.get("age");
		RestAssured.given().body(input.toJSONString());
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

}
