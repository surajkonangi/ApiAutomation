package com.apitraining.Automation.testscripts;

import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetSpecificData {

	@Test
	public void get() {
		String url = "https://dummy.restapiexample.com/api/v1/employees";

		Response response = RestAssured.get(url);
		System.out.println(response.getBody().asString());

		JsonPath jpath = response.jsonPath();
		assertThat(response.getStatusCode(), is(200));
		ArrayList<String> list = jpath.get("data.id");
		System.out.println(list.size());
		String employeename = "Tiger Nixon";
		for (int i = 0; i < list.size(); i++) {
			if ((jpath.get("data.employee_name[" + i + "]")).equals(employeename)) {
				System.out.println("the employee name==>" + jpath.get("data.employee_name[" + i + "]"));
				System.out.println("the employee age==>" + jpath.get("data.employee_age[" + i + "]"));
				System.out.println("the employee salary==>" + jpath.get("data.employee_salary[" + i + "]"));

			}
		}
	}
}
