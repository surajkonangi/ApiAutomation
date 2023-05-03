package com.apitraining.Automation.testscripts;

import org.testng.annotations.Test;

import com.apitraining.Automation.utils.ApiAuth;

public class ApiAwsSignatureAuthCall {

	@Test
	public void aws() { 
		ApiAuth auth = new ApiAuth();
		auth.awssignature();

	}

}
