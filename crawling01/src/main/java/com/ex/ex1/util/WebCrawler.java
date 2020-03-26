package com.ex.ex1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;


public class WebCrawler {

	private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);
	
	//프로세스 처리 시간 체크용 함수
	public static String getCuurentData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
}
