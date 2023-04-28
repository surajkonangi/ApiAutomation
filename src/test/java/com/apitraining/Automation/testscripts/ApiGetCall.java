package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiGetCall {

	public static void main(String[] args) {

		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		Response response = RestAssured.given().baseUri(url).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);

	}

}
