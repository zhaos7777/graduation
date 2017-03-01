package com.stusystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
    public static final int pagesize = 5;
    
	public static String getRadomFileName() {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssms");
		String filename = "";
		filename = dateFormat.format(today);
		int data = (int) (1000 + Math.random() * 9000);
		filename = filename + data;
		System.out.println(filename);
		return filename;
	}
}
