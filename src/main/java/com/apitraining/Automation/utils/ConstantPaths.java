package com.apitraining.Automation.utils;

import java.io.File;

public class ConstantPaths {

	public final static String USER_HOME = System.getProperty("user.dir") + File.separator;
	public final static String Testdata_Properties = USER_HOME + File.separator + "src/main/resources" + File.separator
			+ "properties" + File.separator + "Testdata.properties";
	public final static String Json_Data = USER_HOME + File.separator + "src/main/resources" + File.separator
			+ "jsoninputs" + File.separator + "data.json";
	public final static String Json_postwiremockData = USER_HOME + File.separator + "src/main/resources" + File.separator
			+ "jsoninputs" + File.separator + "postwiremock.json";
	public final static String Json_putwiremockData = USER_HOME + File.separator + "src/main/resources" + File.separator
			+ "jsoninputs" + File.separator + "putwiremock.json";
	public final static String Json_patchwiremockData = USER_HOME + File.separator + "src/main/resources" + File.separator
			+ "jsoninputs" + File.separator + "patchwiremock.json";
	public final static String request = "Get";
}
