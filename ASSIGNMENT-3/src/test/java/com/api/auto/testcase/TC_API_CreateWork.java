package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.Initiator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork extends Initiator {
	protected Response response;
	protected ResponseBody responseBody;
	protected JsonPath jsonBody;
		//Chúng ta có thể tự tạo data

@BeforeClass
//dùng json đưa vào body
		public void requestcreate() {
		File f= new File("./configuration/CreateWorkInfos.json");
	    	
		RequestSpecification request = RestAssured.given()
					.baseUri(baseUrl)
					.contentType(ContentType.JSON)
					.header("token", token)
					.body(f);
			
			response = request.post(createWorkPath);
			responseBody = response.body();
			jsonBody = responseBody.jsonPath();

			System.out.println(" " + responseBody.asPrettyString());
			
		}
		
		@Test(priority = 0)
		public void TC01_Validate201Created() {
	              // kiểm chứng status code
			assertEquals(201, response.getStatusCode(), "Status Check Failed!");

		}



		@Test(priority = 1)
		public void TC02_ValidateWorkId() {
	              // kiểm chứng id
			assertTrue(responseBody.asString().contains("id"), "Id check failed");
			
		}

		@Test(priority = 2)
		public void TC03_ValidateNameOfWorkMatched() {
	              // kiểm chứng tên công việc nhận được có giống lúc tạo
			assertTrue(responseBody.asString().contains("nameWork"), "nameWork check failed");  
			assertEquals(jsonBody.getString("nameWork"), myWork,"nameWork not matching");

		}

		@Test(priority = 3)
		public void TC03_ValidateExperienceMatched() {
	              // kiểm chứng kinh nghiệm nhận được có giống lúc tạo
			assertTrue(responseBody.asString().contains("experience"), "experience check failed");  
			assertEquals(jsonBody.getString("experience"), myExperience,"experience not matching");

		}

		@Test(priority = 4)
		public void TC03_ValidateEducationMatched() {
	              // kiểm chứng học vấn nhận được có giống lúc tạo
			assertTrue(responseBody.asString().contains("education"), "education check failed");  
			assertEquals(jsonBody.getString("education"), myEducation,"education not matching");

		}

}
