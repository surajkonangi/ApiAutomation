package com.apitraining.Automation.testscripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiPostCall {
	public static void main(String arg[]) {

		String url = "https://jsonplaceholder.typicode.com";
		String requestpayload = "{'tittle': 'boo', 'body': 'test', 'userId':'887656'}";

		Response response = RestAssured.given().baseUri(url).body(requestpayload.toString()).when().post("/posts")
				.then().log().all().extract().response();
		System.out.println(response);

	}

}
