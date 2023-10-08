package com.automation.testcase;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {
    @Test(dataProvider = "Excel")
    public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException {
        //Lấy URL từ properties file và tải trang.
        String URL=PropertiesFileUtils.getProperty("base_url");
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Lấy định danh iconSignin từ properties file và tìm kiếm, click
        WebElement iconSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("icon_signin"))));
        iconSignIn.click();
        
        LoginPage loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, loginPage);
        
        //Thực hiện đăng nhập qua loginPage.
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();

        //Lấy định danh iconSignout từ properties,
        //  kiểm tra SignOut có hiển thị ko, nếu hiển thị thì click
         WebElement iconSignout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("icon_signout"))));
         Assert.assertEquals(true, iconSignout.isDisplayed());
         iconSignout.click();

        Thread.sleep(2000);
    }
    

   @DataProvider(name="Excel")
    public Object[][] testDataGenerator() throws IOException {
      //đọc dữ liệu đầu vào từ file excel	
	   FileInputStream File = new FileInputStream("./data/assignment2_data_test.xlsx");
	   XSSFWorkbook workbook = new XSSFWorkbook(File);
	   XSSFSheet loginSheet = workbook.getSheet("Login");
	   int NumberofData=loginSheet.getPhysicalNumberOfRows();
	   Object [][] data=new Object[NumberofData][2];
	   
	   for (int i=0;i<NumberofData;i++) {
		   XSSFRow row=loginSheet.getRow(i);
		   XSSFCell email=row.getCell(0);
		   XSSFCell password=row.getCell(1);
		   data[i][0]= email.getStringCellValue();
		   data[i][1]=password.getStringCellValue();
	   }
	    for (int i = 0; i < NumberofData; i++) {
	        for (int j = 0; j < 2; j++) {
	            System.out.print(data[i][j] + "\t");}}
    	 return data;
    }   


    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        //ITestResult để lấy trạng thái và tên và tham số của từng Test Case
        // phương thức này sẽ được gọi mỗi khi @Test thực thi xong,
        // ta có thể kiểm tra kết quả testcase tại đây.
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
               //1. Lấy tham số (parameters) đầu vào của TC vừa chạy
               //email:0, password:1
               String email = (String)result.getParameters()[0];
                   
         //2. Lấy ra phần tên trong email để làm tên của screenshot
         // Tìm vị trí(int index) của ký tự ‘@’ và lấy ra chuỗi con
         // đứng trước @  qua thư viện của String là: indexOf() và subString()                  
              int index =email.indexOf("@");
              String accountName =email.substring(0,index)  ; 
              CaptureScreenshot.takeScreenshot(driver, accountName);
            } catch (Exception e) {
                System.out.println("Lỗi xảy ra screenshot " + e.getMessage());
            }
        }  
    }

}
