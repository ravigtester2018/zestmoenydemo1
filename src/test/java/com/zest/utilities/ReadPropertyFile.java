package com.zest.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
	static InputStream input;
	static Properties prop;

	public ReadPropertyFile() throws IOException {
		input = new FileInputStream("D:/WS6_ZestMoney/money/src/test/java/com/zest/utilities/config.properties");
		// load a properties file
		prop = new Properties();
		// get the property value and print it out
		prop.load(input);
	}

	public String returnPropertyValue(String s) throws IOException {
		System.out.println(prop.getProperty(s));
		return prop.getProperty(s);
	}

}
