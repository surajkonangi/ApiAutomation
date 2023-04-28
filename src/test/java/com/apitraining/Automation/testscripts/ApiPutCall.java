package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiPutCall {

	public static void main(String[] args) {

		String url = "https://jsonplaceholder.typicode.com";
		String requestpayload = "{'id':'101', tittle': 'book', 'body': 'test', 'userId':'887656'}";

		Response response = RestAssured.given().baseUri(url).body(requestpayload.toString()).when().put("/posts/1").then()
				.log().all().extract().response();
		System.out.println(response);

	}

}
