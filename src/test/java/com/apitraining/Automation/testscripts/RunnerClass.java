package com.apitraining.Automation.testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RunnerClass extends ParentClass{
  @Test
  public void post() {
	
		Response response = RestAssured.given().when().post("/create")
				.then().log().all().extract().response();
		System.out.println(response);
  }
}
