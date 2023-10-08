package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.Initiator;
import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_Login extends Initiator {

	private  Response response;
	private ResponseBody responseBody;
	private  JsonPath jsonBody;

	@BeforeClass
	 public void init() {

		// make body
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("account",account);
		body.put("password", password);
		
        RequestSpecification request = RestAssured.given()
            					// .log().all() - de xem chi tiet request
            					.baseUri(baseUrl)
                                .contentType(ContentType.JSON)
                                .body(body);

		response = request.post(loginpath);
		responseBody = response.body();
		jsonBody = responseBody.jsonPath();
		
		System.out.println(" " + responseBody.asPrettyString());
	}

	@Test(priority = 0)
	public void TC01_Validate200Ok() {
              // kiểm chứng status code
		assertEquals(200, response.getStatusCode(), "Status Check Failed!");
	}

	@Test(priority = 1)
	public void TC02_ValidateMessage() {
              // kiểm chứng response body có chứa trường message hay không
              // kiểm chứng trường message có = "Đăng nhập thành công
	assertTrue(responseBody.asString().contains("message"), "message check failed");
	String messcontent= jsonBody.getString("message");
	assertEquals(true, messcontent.contains("Đăng nhập thành công"),"Message not contain valid content!");
	}

	@Test(priority = 2)
	public void TC03_ValidateToken() {
           // kiểm chứng response body có chứa trường token hay không
		assertTrue(responseBody.asString().contains("token"), "token check failed");
		String token = jsonBody.getString("token");;
		PropertiesFileUtils.saveToken(token);;
	}

	@Test(priority = 3)
	public void TC05_ValidateUserType() {
         // kiểm chứng response body có chứa thông tin user và trường type hay không
         // kiểm chứng trường type có phải là “UNGVIEN”
		assertEquals(true,responseBody.asString().contains("user"));
		assertEquals(true,responseBody.asString().contains("type"), "type check failed");
		String type=jsonBody.getString("user.type");
		assertEquals(true,type.contains("UNGVIEN"),"type not contain valid content!");
	}

	@Test(priority = 4)
	public void TC06_ValidateAccount() {
          // kiểm chứng response chứa thông tin user và trường account hay không
		assertEquals(true,responseBody.asString().contains("user"),"user check failed");
		assertEquals(true,responseBody.asString().contains("account"),"account check failed");
          // Kiểm chứng trường account có khớp với account đăng nhập
		assertEquals(jsonBody.getString("user.account"), account,"Account not matching");
          // kiểm chứng response chứa thông tin user và trường password hay không
		assertEquals(true,responseBody.asString().contains("password"),"Password check failed");
          // Kiểm chứng trường password có khớp với password đăng nhập
		assertEquals(jsonBody.getString("user.password"), password,"Password not matching");
	}
}


