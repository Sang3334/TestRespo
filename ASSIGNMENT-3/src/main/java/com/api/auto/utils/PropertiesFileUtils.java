package com.api.auto.utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.io.File;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class PropertiesFileUtils {
	// Đường dẫn đến properties files trong folder configuration
	private static String CONFIG_PATH = "./configuration/configs.properties";
	private static String TOKEN_PATH = "./configuration/token.json";
	private static String JSON_PATH = "./configuration/CreateWorkInfos.json";

	// 1 Lấy ra giá trị property từ config file bất kỳ theo key.
	public static String getProperty(String key) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		// bat exception
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của  " + key);
			ex.printStackTrace();
		} finally {
			// luôn nhảy vào đây dù có xảy ra exception hay không.
			// thực hiện đóng luồng đọc
			if (fileInputStream != null) {
				try {
				fileInputStream.close();
				} catch (IOException e) {
				e.printStackTrace();
		}}}
		return value;
	}
	// 2.save token vào file token.json trong configuration
	public static void saveToken(String token) {
             // Khái báo properties, biến cần thiết
		Properties properties= new Properties();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(TOKEN_PATH), token);
	        System.out.println("Set new value in file properties success.");
		} catch (IOException ex) {
			System.out.println("Xảy ra lỗi khi luu gia tri token  ");
			ex.printStackTrace();
		} 
	}

	// 3 lấy ra token
	public static String getToken() {
             // Khái báo properties, biến, value
		FileInputStream fileInputStream = null;
		// bad exception
		try {
			fileInputStream = new FileInputStream(TOKEN_PATH);
			ObjectMapper mapper = new ObjectMapper();
			String value = mapper.readValue(fileInputStream,String.class);
			
			return value;

		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị token  ");
			ex.printStackTrace();
		} finally {
			// Luôn nhảy vào đây dù có xảy ra exception hay không.
			// thực hiện đóng luồng
			if (fileInputStream != null) {
				try {
				fileInputStream.close();
				} catch (IOException e) {
				e.printStackTrace();
		}}}
		return null;	
	}
	//lay gia tri tu file json
	public static String getvalueofJson(String key){
		FileInputStream fileInputStream = null;
		try {
		fileInputStream = new FileInputStream(JSON_PATH);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(fileInputStream);
		
		String value = root.get(key).asText();

		return value;

	} catch (Exception ex) {
		System.out.println("Xảy ra lỗi khi lay gia tri json  ");
		ex.printStackTrace();
	}finally {
		// Luôn nhảy vào đây dù có xảy ra exception hay không.
		// thực hiện đóng luồng
		if (fileInputStream != null) {
			try {
			fileInputStream.close();
			} catch (IOException e) {
			e.printStackTrace();
		}}}
		
		return null;
}}



