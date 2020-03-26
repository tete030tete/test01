package com.ex.ex1.selenium.getrend;

import java.util.ArrayList;
import java.util.HashMap;

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

public class instagram_Selenium_header {

	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_header.class);
	
	//WebDriver
	private WebDriver driver;
	private WebElement element;

	public HashMap<String, Object> header(ArrayList<String> instaid) throws Exception {
	

		 HashMap<String, Object> map = new HashMap<String, Object>();
		 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("headless");
		 options.addArguments("window-size=1920x1080");
         options.addArguments("disable-gpu");
         options.addArguments("disable-infobars");
         options.addArguments("--disable-extensions");
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
		   		//String loginPage = "https://www.instagram.com/accounts/login/?next=%2F"+ instaid.get(i) +"%2F&source=desktop_nav";
				String page = "https://www.instagram.com/" + instaid.get(i);
				driver.get(page); 
				
		      /*if(i == 0) {
		       	//로그인
		       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
		       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
		       	 loginId.clear();
		       	 loginId.sendKeys("01024569637");
		       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
		       	 loginPwd.clear();
		       	 loginPwd.sendKeys("tete030");
		       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
			 }*/

			JavascriptExecutor js =(JavascriptExecutor)driver;    
			Document doc = Jsoup.parse(driver.getPageSource()); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._6q-tv")));
			
			System.out.println("======================================================");
			System.out.println("HTML TITLE : " + doc.title()); 
			
			//가게 대표 사진
			Element storeimg = doc.selectFirst("._6q-tv");
			String storehref = storeimg.outerHtml();
			System.out.println("대표사진 : " + storehref);
			map.put("storehref", storehref);
			
			//가게 팔로워
			Element storefollower2 = doc.selectFirst(".g47SY");
			String storefollower = storefollower2.text();
			System.out.println("가게 팔로워 : " + storefollower);
			map.put("storefollower", storefollower);
			 
			//가게 프로필
			Element storeprofile2 = doc.selectFirst(".-vDIg > span");
			String storeprofile = storeprofile2.text();
			System.out.println("가게프로필 : " + storeprofile);
			map.put("storeprofile", storeprofile);
	        
			System.out.println("======================================================");
		 
		 } catch(Exception e) {
				System.out.println("에러");
		   	}//catch
		}//for
			
		driver.close();
		return map;
	}
	
	
}
