package com.apitraining.Automation.testngpractice;

import org.testng.annotations.Test;

import com.apitraining.Automation.utils.TestngUtils;

import io.restassured.RestAssured;

public class RunnerClass{
	
	@Test
	
	public void method() throws Exception {
	TestngUtils runapicall = new TestngUtils();
	runapicall.beforeSuite();
	runapicall.beforeTest();
	runapicall.apiCalls();
	runapicall.afterTest();
	runapicall.afterSuite();
	
	}
	}

