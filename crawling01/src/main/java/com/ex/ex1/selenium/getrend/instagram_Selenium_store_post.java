package com.ex.ex1.selenium.getrend;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.aspectj.lang.reflect.CatchClauseSignature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class instagram_Selenium_store_post {

	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_store_post.class);
	
	//WebDriver
	private WebDriver driver;
	private WebElement element;
	
	public HashMap<String, Object> post_list(ArrayList<String> instaid) throws Exception {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 ArrayList<String> posturllist = new ArrayList<String>();
	     ArrayList<String> postlikelist = new ArrayList<String>();
		 ArrayList<String> postlist = new ArrayList<String>();
		 int cnt = 1;
		 String posturl;
		 WebElement like_span;
		 WebElement post_span;
		 
		 
		 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("headless");
		 options.addArguments("window-size=1920x1080");
         options.addArguments("disable-gpu");
         options.addArguments("disable-infobars");
         options.addArguments("--disable-extensions");
         
         //둘중하난데.. 
         //options.addArguments("--user-agent='Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36'");
         options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        
         options.setProxy(null);
         HashMap<String, Object> prefs = new HashMap<String, Object>();
		 prefs.put("profile.default_content_setting_values.cookies", 2);
		 prefs.put("profile.default_content_setting_values.images", 2);
		 prefs.put("profile.default_content_setting_values.plugins", 2);
		 prefs.put("profile.default_content_setting_values.popups", 2);
		 prefs.put("profile.default_content_setting_values.geolocation", 2);
		 prefs.put("profile.default_content_setting_values.notifications", 2);
		 prefs.put("profile.default_content_setting_values.auto_select_certificate", 2);
		 prefs.put("profile.default_content_setting_values.fullscreen", 2);
		 prefs.put("profile.default_content_setting_values.mouselock", 2);
		 prefs.put("profile.default_content_setting_values.mixed_script", 2);
		 prefs.put("profile.default_content_setting_values.media_stream", 2);
		 prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
		 prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
		 prefs.put("profile.default_content_setting_values.protocol_handlers", 2);
		 prefs.put("profile.default_content_setting_values.ppapi_broker", 2);
		 prefs.put("profile.default_content_setting_values.automatic_downloads", 2);
		 prefs.put("profile.default_content_setting_values.midi_sysex", 2);
		 prefs.put("profile.default_content_setting_values.push_messaging", 2);
		 prefs.put("profile.default_content_setting_values.ssl_cert_decisions", 2);
		 prefs.put("profile.default_content_setting_values.metro_switch_to_desktop", 2);
		 prefs.put("profile.default_content_setting_values.protected_media_identifier", 2);
		 prefs.put("profile.default_content_setting_values.app_banner", 2);
		 prefs.put("profile.default_content_setting_values.site_engagement", 2);
		 prefs.put("profile.default_content_setting_values.durable_storage", 2);
		 options.setExperimentalOption("prefs", prefs);
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
         capabilities.setCapability(ChromeOptions.CAPABILITY, options);
         capabilities.setCapability("pageLoadStrategy", "none");
		 
         
         driver = new ChromeDriver(options);  
		 WebDriverWait wait = new WebDriverWait(driver, 3);				 
		 
	 
		for(int i = 0; i < instaid.size() ; i++ ) { 
			 
		   try {
		   	 String loginPage = "https://www.instagram.com/accounts/login/?next=%2F"+ instaid.get(i) +"%2F&source=desktop_nav";
			 driver.get(loginPage); 
				
				if(i == 0) {
		       	//로그인
		       	 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
		       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
		       	 loginId.clear();
		       	 loginId.sendKeys("01024569637");
		       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
		       	 loginPwd.clear();
		       	 loginPwd.sendKeys("tete030");
		       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
				}
				
				
			 JavascriptExecutor js =(JavascriptExecutor)driver;    
			 Document doc = Jsoup.parse(driver.getPageSource()); 
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".FFVAD")));
			
			 System.out.println("======================================================");
			 System.out.println("HTML TITLE : " + doc.title()); 
			
			 //첫번째 포스트 클릭
	         js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".FFVAD")));            
	         Thread.sleep(3000);
	         while(cnt <= 9) {
	        	
	        	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".EDfFK.ygqzn")));
	    		posturl =  driver.getCurrentUrl();
		        System.out.println(cnt + "번 포스트 url : " + posturl);
		        posturllist.add(posturl);

		        like_span = driver.findElement(By.cssSelector(".EDfFK.ygqzn span"));
		        System.out.println(cnt + "번 포스트 좋아요 : " + like_span.getText());
		        postlikelist.add(like_span.getText());
		        
		        post_span = driver.findElement(By.cssSelector(".C4VMK span"));
		        System.out.println(cnt + "번 포스트 글 : " + post_span.getText());
		        postlist.add(post_span.getText());
		        
	    		//포스트 모달의 오른쪽 이동 버튼 눌러 다음 포스트로 화면 전환
	    		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")));    
	    		driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();
	    		cnt++;
	    	
	         }//while   
   
	         map.put("posturllist", posturllist);
	         map.put("postlikelist", postlikelist);
	         map.put("postlist", postlist);
	         cnt = 1;
			 System.out.println("======================================================");
		 
		  } catch(Exception e) {
			 	e.printStackTrace();
				System.out.println(cnt + "에러");
		    	}
		 }//for
			
		 driver.close();
		 return map;
	}
	
	
}
