package com.apitraining.Automation.testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RunnerClass extends ParentClass {
	@Test
	public void post() {
		response = RestAssured.given().contentType(prop.getProperty("content")).when().post("/create").then().log().all()
				.extract().response();
		System.out.println(response);
	}
}
