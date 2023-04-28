package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiPatchCall {

	public static void main(String[] args) {
		String url = "https://jsonplaceholder.typicode.com";
		String requestpayload = "{'tittle': 'book',}";

		Response response = RestAssured.given().baseUri(url).body(requestpayload.toString()).when().patch("/posts/1").then()
				.log().all().extract().response();
		System.out.println(response);


	}

}
