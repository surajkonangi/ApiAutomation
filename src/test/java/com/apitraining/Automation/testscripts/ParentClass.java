package com.apitraining.Automation.testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ParentClass {
	private Response response;
	
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() throws Exception {
	  Properties prop = new Properties();
	  FileInputStream path = new FileInputStream("C:\\Users\\suraj.konangi\\eclipse-workspace\\Automation\\src\\main\\resources\\Testdata.properties");
	  prop.load(path);
	  JSONObject input = new JSONObject();
		input.put("name", prop.getProperty("name"));
		input.put("salary", prop.getProperty("salary"));
		input.put("age",prop.getProperty("age"));
	   response = RestAssured.given().contentType(prop.getProperty("content")).body(input.toString()).auth().basic(prop.getProperty("user"),prop.getProperty("password")).when().post("/create").then().log()
				.all().extract().response();
		System.out.println(response);
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() throws Exception {
	  Properties prop = new Properties();
//	  FileInputStream path = new FileInputStream("C:\\Users\\suraj.konangi\\eclipse-workspace\\Automation\\src\\main\\resources\\Testdata.properties");
//	  prop.load(path);
	  JSONObject input = new JSONObject();
		input.put("name", prop.getProperty("name"));
		input.put("salary", prop.getProperty("salary"));
		input.put("age",prop.getProperty("age"));
		RestAssured.given().body(input.toString());
  }

  @AfterTest
  public void afterTest() {
	  
	  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" , "Validated Success");
  }

  @BeforeSuite
  public void beforeSuite() throws Exception {
	  Properties prop = new Properties();
	  FileInputStream path = new FileInputStream("C:\\Users\\suraj.konangi\\eclipse-workspace\\Automation\\src\\main\\resources\\Testdata.properties");
	  prop.load(path);
	  RestAssured.baseURI = prop.getProperty("url");
	  
  }

  @AfterSuite
  public void afterSuite() {
  }

}
