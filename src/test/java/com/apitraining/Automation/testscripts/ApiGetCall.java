package com.apitraining.Automation.testscripts;
import org.testng.annotations.Test;

import com.apitraining.Automation.utils.AllApiMethods;

public class ApiGetCall {
	@Test
	public void getcall() {
		AllApiMethods get = new AllApiMethods();
		get.apigetcall();

	}

}
