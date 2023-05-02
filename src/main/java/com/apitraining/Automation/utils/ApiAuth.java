package com.apitraining.Automation.utils;

import com.apitraining.Automation.interfac.AllAuthApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAuth implements AllAuthApi{

	
		
	public void akamaiedgegrid() {
		
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
		Response response = RestAssured.given().baseUri(url).header("Authentication", token).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
		
	}
	
	public void bearertoken () {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

		Response response = RestAssured.given().baseUri(url).header("Authentication", token).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
		
		
	}
	
	public void awssignature() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String keys = "{'accesskey':'ADESEYW4U65ECCVHG','secretkey':'HBDJHV723YGDYWGED6723'}";
	
		Response response = RestAssured.given().baseUri(url).header("Authentication",keys).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}

	public void basicauth() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String username = "Suraj Konangi";
		String password = "Suraj123";
				
		Response response = RestAssured.given().baseUri(url).auth().basic(username, password).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);

	}
	
	public void digestauth() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String username = "Suraj Konangi";
		String password = "Suraj123";
		
		
		Response response = RestAssured.given().baseUri(url).auth().digest(username, password).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	
	public void hawkauth() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String keys = "{'hawkauthid':'surajkonangi','hawkauthkey':'HBDJHV723YGDYWGED6723'}";
		
		Response response = RestAssured.given().baseUri(url).header("Authentication",keys).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	
	public void jwtbearer() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String secret = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
		Response response = RestAssured.given().baseUri(url).header("Authentication", secret).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	
	public void keyauth() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String keyvalue = "X-Api-Key:your_api_key_here";
		
		Response response = RestAssured.given().baseUri(url).header("Authentication",keyvalue).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	
	public void ntlmauth() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String username = "Suraj Konangi";
		String password = "Suraj123";
		String Domain = "My Domine";
		String workstation = "My Work";
		
		
		Response response = RestAssured.given().baseUri(url).auth().ntlm(username, password, workstation, Domain).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	
	public void oathone() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String signaturemethod = "HMAC-SHA1";
		String consumerkey = "60wyd s546nyebds73hwdbsy64y27";
		String consumersecret = "sk12ker90";
		String accesstoken = "b3retds434sgertryhsndv6463";
	
		
		
		Response response = RestAssured.given().baseUri(url).header(signaturemethod, consumerkey, consumersecret, accesstoken).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);

	}
	
	public void oathtwo() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		String requestparams = "id";
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	
		
		
		Response response = RestAssured.given().baseUri(url).auth().oauth2(token).param(requestparams, 1).when().get().then().log()
				.all().extract().response();
		System.out.println(response);
	}
	

}
