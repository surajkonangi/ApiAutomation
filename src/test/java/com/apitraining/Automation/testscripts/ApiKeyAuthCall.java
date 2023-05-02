package com.apitraining.Automation.testscripts;

import com.apitraining.Automation.utils.ApiAuth;

public class ApiKeyAuthCall {

	public static void main(String[] args) {
		ApiAuth auth = new ApiAuth();
		auth.keyauth();;
	}

}
