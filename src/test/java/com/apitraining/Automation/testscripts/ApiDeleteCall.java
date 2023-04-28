package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiDeleteCall {

	public static void main(String[] args) {
		String url = "https://jsonplaceholder.typicode.com/posts";
		//String requestparams = "id";
		Response response = RestAssured.given().baseUri(url).when().delete("/1").then().log()
				.all().extract().response();
		System.out.println(response);

	}

}
