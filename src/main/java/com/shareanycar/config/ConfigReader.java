package com.shareanycar.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.jvnet.hk2.annotations.Service;

@Service
public class ConfigReader {
	private Properties properties;

	public ConfigReader() {
		properties = new Properties();
		Reader reader;
		
		try {			
			
			reader = new FileReader(getClass().getClassLoader().getResource("app.settings").getFile());
			
			properties.load(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
