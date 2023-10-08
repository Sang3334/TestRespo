package com.automation.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class CaptureScreenshot {
	
	static String filename=null;
	
	public static void takeScreenshot(WebDriver driver, String name) {
    try {
        //Tạo tên ảnh trùng với tên testcase, kiểu ảnh là png
      //  String imageName = name + ".png";

        // Thực hiện chụp ảnh màn hình, lấy ra đối tượng file ảnh source.
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        filename="./screenshots/"+ name + ".png";
        File source = screenshot.getScreenshotAs(OutputType.FILE);

        //Tạo đối tượng file với tên đã đặt bên trên tại thư mục /ScreenShots,
        // Sau đó thực viện cope file ảnh nguồn vào file đích đó.
        File destiny = new File(filename);
        FileHandler.copy(source, destiny);

    } catch (Exception ex) {
        System.out.println("Đã xảy ra lỗi khi chụp màn hình!");
        ex.printStackTrace();
    }
    CaptureScreenshot.attachScreenshotToReport(filename);
    }
	
	public static void attachScreenshotToReport(String filename) {
	try {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File f = new File(filename);
		Reporter.log("<br> <a title=\"screenshots\"href=\""+ f.getAbsolutePath()+ "\">" +"<img alt='" + f.getName()+ "'src='"+  f.getAbsolutePath()+ "' height='243' width='418' /> </a><br>");
	
	} catch (Exception e) {
			System.out.println("Xay ra loi khi chup man hinh");
	}}
}

