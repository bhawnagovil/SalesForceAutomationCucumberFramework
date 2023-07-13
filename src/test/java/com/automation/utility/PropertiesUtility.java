package com.automation.utility;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
    public FileReader stream = null;
	public Properties propFile = null;
	
	
	public Properties loadFile(String filename) {
		propFile=new Properties();
		String propertyFilePath=null;
		switch(filename) {
		case "applicationDataProperties":
			propertyFilePath=Constants.APPLICATION_Data_PROPERTIES;
			System.out.println("propertyFilePath :"+ propertyFilePath );
			break;
		case "testDataProperties":
			propertyFilePath=Constants.test_Data_PROPERTIES;
			break;
			
		}
		try {
			stream=new FileReader(propertyFilePath);
			propFile.load(stream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return propFile;
		
	}

	public String getPropertyValue(String key) {
		String value = propFile.getProperty(key);
		return value;

	}

	public void writeDataToPropertyFile(File path, String key, String value) {

		Properties propFile = new Properties();
		propFile.setProperty(key, value);
		try {
			propFile.store(new FileOutputStream(path), "updating data");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
