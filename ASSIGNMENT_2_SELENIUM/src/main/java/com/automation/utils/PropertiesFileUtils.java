package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	//Đường dẫn đến properties files trong folder configuration
		private static String CONFIG_PATH = "./configuration/configs.properties";
		
		//1 Lấy ra giá trị property bất kỳ theo key.
		public static String getProperty(String key) {
		Properties properties= new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		//bad exception
		try {
		fileInputStream = new FileInputStream(CONFIG_PATH);
		properties.load(fileInputStream);
		value = properties.getProperty(key);
		return value;
		} catch (Exception ex) {
		System.out.println("Xảy ra lỗi khi đọc giá trị của" + key);
		ex.printStackTrace();
		} finally {
		//luôn nhảy vào đây dù có xảy ra exception hay không.
		//thực hiện đóng luồng đọc
		if (fileInputStream != null) {
		try {
		fileInputStream.close();
		} catch (IOException e) {
		e.printStackTrace();
		}}}
		return value;
		}
}
