package com.apitraining.Automation.testscripts;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetSpecificData {

	private Pattern pattern;

	@Test
	public void get() {
		pattern = Pattern.compile("^([0-9]+)");
		String url = "https://dummy.restapiexample.com/api/v1/employees";
		Response response = RestAssured.given().get(url);
		System.out.println(response.getBody().asString());

		JsonPath jpath = response.jsonPath();
		assertThat(response.getStatusCode(), is(200));
		ArrayList<String> list = jpath.get("data.id");
		System.out.println(list.size());
		Matcher match = pattern.matcher("170750");
		if (match.matches()) {
			System.out.println(match.group(0));
		}
		for (int i = 0; i < list.size(); i++) {

			if ((jpath.get("data.employee_salary[" + i + "]")).toString().equals(match.group(0))) {
				System.out.println(match.group(0));
				System.out.println("the employee name==>" + jpath.get("data.employee_name[" + i + "]"));
				System.out.println("the employee salary==>" + jpath.get("data.employee_salary[" + i + "]"));
				System.out.println("the employee age==>" + jpath.get("data.employee_age[" + i + "]"));

			}

		}

	}

}
