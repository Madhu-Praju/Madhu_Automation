package com.qea.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class fileReaderUtility {
	
	public FileInputStream fileReaderr(String filepath, String filename) throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(new File(filepath+"\\"+filename));
		
		return inputStream;
	}

}
