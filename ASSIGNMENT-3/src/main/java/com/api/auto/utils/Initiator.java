package com.api.auto.utils;

import org.testng.annotations.BeforeTest;

public class Initiator {

	protected String baseUrl ;
	protected String createWorkPath;
	protected String token ;
	protected String account ;
	protected String password ;
	protected String loginpath;
	protected String myWork	;
	protected String myExperience;
	protected String myEducation;
@BeforeTest

	public void intit() {
	baseUrl = PropertiesFileUtils.getProperty("baseurl");
	createWorkPath =PropertiesFileUtils.getProperty("createWorkPath");
	token = PropertiesFileUtils.getToken();
	account = PropertiesFileUtils.getProperty("account");
	password = PropertiesFileUtils.getProperty("password");
	loginpath= PropertiesFileUtils.getProperty("loginPath");
	myWork=PropertiesFileUtils.getvalueofJson("nameWork");
	myExperience=PropertiesFileUtils.getvalueofJson("experience");
	myEducation=PropertiesFileUtils.getvalueofJson("education");
}
}


