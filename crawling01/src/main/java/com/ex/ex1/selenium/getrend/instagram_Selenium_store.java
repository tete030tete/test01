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

public class instagram_Selenium_store {

	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_store.class);
	
	//WebDriver
	private WebDriver driver;
	private WebElement element;

	public HashMap<String, Object> header(ArrayList<String> instaid) throws Exception {
	

		 HashMap<String, Object> map = new HashMap<String, Object>();
		 ArrayList<String> posturllist = new ArrayList<String>();
	     ArrayList<String> postlikelist = new ArrayList<String>();
		 ArrayList<String> postlist = new ArrayList<String>();
	
		 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
		 
		 driver = new ChromeDriver();  
		 WebDriverWait wait = new WebDriverWait(driver, 3);		
	 
		for(int i = 0; i < instaid.size() ; i++ ) {
			 
		   	try {
		   		String loginPage = "https://www.instagram.com/accounts/login/?next=%2F"+ instaid.get(i) +"%2F&source=desktop_nav";
				//String page = "https://www.instagram.com/" + instaid.get(i);
				driver.get(loginPage); 
				
		      if(i == 0) {
		       	//로그인
		       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
		       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
		       	 loginId.clear();
		       	 loginId.sendKeys("01024569637");
		       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
		       	 loginPwd.clear();
		       	 loginPwd.sendKeys("tete030");
		       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
			 }

	       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v1Nh3.kIKUG._bz0w > a")));    
	       	 System.out.println("======================================================");
	       	 System.out.println("가게 :  " + driver.getTitle());
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
			
			//가게 포스트
			//첫번째 포스트 href
			int cnt = 1;
	    	Element post_first = doc.selectFirst(".v1Nh3.kIKUG._bz0w > a");
	        String posturl = post_first.attr("href");
	        System.out.println(cnt + "번 post url : https://www.instagram.com" + posturl);
	        posturllist.add(posturl);
	        
	        
	        //첫번째 포스트 클릭
	        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".FFVAD")));            
	        
	        //첫번째 포스트 좋아요 
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".EDfFK.ygqzn")));
	        WebElement like_span = driver.findElement(By.cssSelector(".EDfFK.ygqzn span"));
	        
	        
	        System.out.println("좋아요 : " + like_span.getText());
	        postlikelist.add(like_span.getText());
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".C4VMK")));
	        WebElement post_span = driver.findElement(By.cssSelector(".C4VMK span"));
	        System.out.println(cnt + "번 포스트 글 : " + post_span.getText());
	        postlist.add(post_span.getText());
	        
	        
	        
	        while(cnt < 30) {
	    		cnt++;
	    		//포스트 모달의 오른쪽 이동 버튼 눌러 다음 포스트로 화면 전환
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")));    
	    		driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();
	    		posturl =  driver.getCurrentUrl();
		        System.out.println(cnt + "번 포스트 url : " + posturl);
		        posturllist.add(posturl);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".EDfFK.ygqzn")));
		        like_span = driver.findElement(By.cssSelector(".EDfFK.ygqzn span"));
		        System.out.println("좋아요 : " + like_span.getText());
		        postlikelist.add(like_span.getText());
		        
		        post_span = driver.findElement(By.cssSelector(".C4VMK span"));
		        System.out.println(cnt + "번 포스트 글 : " + post_span.getText());
		        postlist.add(post_span.getText());
		        
	        }//while   

	        
	        map.put("posturllist", posturllist);
	        map.put("postlikelist", postlikelist);
	        map.put("postlist", postlist);
	        
			System.out.println("======================================================");
		 
		 } catch(Exception e) {
				
		   		String loginPage = "https://www.instagram.com/accounts/login/?next=%2F"+ instaid.get(i) +"%2F&source=desktop_nav";
					//String page = "https://www.instagram.com/" + instaid.get(i);
					driver.get(loginPage); 
				
			      if(i == 0) {
			       	//로그인
			       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
			       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
			       	 loginId.clear();
			       	 loginId.sendKeys("01024569637");
			       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
			       	 loginPwd.clear();
			       	 loginPwd.sendKeys("tete030");
			       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
				 }
			 
			 
		       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v1Nh3.kIKUG._bz0w > a")));    
		       	 System.out.println("======================================================");
		       	 System.out.println("가게 :  " + driver.getTitle());
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
				
				//가게 포스트
				//첫번째 포스트 href
				int cnt = 1;
		    	Element post_first = doc.selectFirst(".v1Nh3.kIKUG._bz0w > a");
		        String posturl = post_first.attr("href");
		        System.out.println(cnt + "번 post url : https://www.instagram.com" + posturl);
		        posturllist.add(posturl);
		        
		        //첫번째 포스트 클릭
		        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".FFVAD")));            
		        //첫번째 포스트 좋아요 
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".EDfFK.ygqzn")));
		        WebElement like_span = driver.findElement(By.cssSelector(".EDfFK.ygqzn span"));
		        
		        System.out.println("좋아요 : " + like_span.getText());
		        postlikelist.add(like_span.getText());
		        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".C4VMK")));
		        WebElement post_span = driver.findElement(By.cssSelector(".C4VMK span"));
		        System.out.println(cnt + "번 포스트 글 : " + post_span.getText());
		        postlist.add(post_span.getText());
		        
		        while(cnt < 30) {
		    		cnt++;
		    		//포스트 모달의 오른쪽 이동 버튼 눌러 다음 포스트로 화면 전환
		    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")));    
		    		driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();
		    		posturl =  driver.getCurrentUrl();
			        System.out.println(cnt + "번 포스트 url : " + posturl);
			        posturllist.add(posturl);
			        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".EDfFK.ygqzn")));
			        like_span = driver.findElement(By.cssSelector(".EDfFK.ygqzn span"));
			        System.out.println("좋아요 : " + like_span.getText());
			        postlikelist.add(like_span.getText());
			        
			        post_span = driver.findElement(By.cssSelector(".C4VMK span"));
			        System.out.println(cnt + "번 포스트 글 : " + post_span.getText());
			        postlist.add(post_span.getText());
		        }//while   
		        
		        map.put("posturllist", posturllist);
		        map.put("postlikelist", postlikelist);
		        map.put("postlist", postlist);
		        
				System.out.println("======================================================");
			 
		   	
		   	}
		}//for
			
			
			
		driver.close();
		return map;
	}
	
	
}
