package com.apitraining.Automation.utils;

import org.json.JSONObject;
import org.testng.Assert;

import com.apitraining.Automation.interfac.AllMethodApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AllApiMethods implements AllMethodApi{

	public void apigetcall() {
		

		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String secret = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
		Response response = RestAssured.given().baseUri(url).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
		
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" , "Validated Success");
		

	}
	
	public void apipostcall() {
		JSONObject input = new JSONObject();
		input.put("tittle", "boo");
		input.put("body", "test");
		input.put("userId","887656");
		String url = "https://jsonplaceholder.typicode.com";
		//String requestpayload = "{'tittle': 'boo', 'body': 'test', 'userId':'887656'}";

		Response response = RestAssured.given().baseUri(url).body(input.toString()).when().post("/posts")
				.then().log().all().extract().response();
		System.out.println(response);
	}
	
	public void apiputcall() {
		String url = "https://jsonplaceholder.typicode.com";
		String requestpayload = "{'id':'101', tittle': 'book', 'body': 'test', 'userId':'887656'}";

		Response response = RestAssured.given().baseUri(url).body(requestpayload.toString()).when().put("/posts/1").then()
				.log().all().extract().response();
		System.out.println(response);
	}
	
	public void apipatchcall() {
		String url = "https://jsonplaceholder.typicode.com";
		String requestpayload = "{'tittle': 'book',}";

		Response response = RestAssured.given().baseUri(url).body(requestpayload.toString()).when().patch("/posts/1").then()
				.log().all().extract().response();
		System.out.println(response);
	}
	
	public void apideletecall() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		//String requestparams = "id";
		Response response = RestAssured.given().baseUri(url).when().delete("/1").then().log()
				.all().extract().response();
		System.out.println(response);

	}
}
