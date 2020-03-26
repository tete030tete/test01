package com.ex.ex1.selenium;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_post_list {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_post_list.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

	
	//로그인 후 포스트 url 30개 가져오기 * 10가게 반복 
	
    public ArrayList<String> post_list(String instaid) {
    	ArrayList<String> re = new ArrayList<String>();
    	try {
    		
    		
    	 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
         driver = new ChromeDriver();        
    	 WebDriverWait wait = new WebDriverWait(driver, 5);
         int cnt;
         String current;
         for (int i = 0; i < 5; i++) {
        	 
        	cnt = 1;
        	 //로그인
	      	 String loginPage = "https://www.instagram.com/accounts/login/?next=%2F" + instaid + "%2F&source=desktop_nav";
	       	 driver.get(loginPage);
	       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
	       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
	       	 loginId.clear();
	       	 loginId.sendKeys("tete030tete@gmail.com");
	       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
	       	 loginPwd.clear();
	       	 loginPwd.sendKeys("tete030");
	       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
        
	       	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v1Nh3.kIKUG._bz0w > a")));    
        	System.out.println("======================================================");
        	System.out.println("가게 :  " + driver.getTitle());
	        
        	//첫번째 포스트 href
        	Document doc = Jsoup.parse(driver.getPageSource());
        	Element post_first = doc.selectFirst(".v1Nh3.kIKUG._bz0w > a");
	        String post_href = post_first.attr("href");
	        System.out.println(cnt + "번 post url : https://www.instagram.com" + post_href);
	        re.add("https://www.instagram.com" + post_href);
        
	        //첫번째 포스트 클릭
	        JavascriptExecutor js =(JavascriptExecutor)driver;    
	        Thread.sleep(1000);
            js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".FFVAD")));            
            
            while(cnt < 30) {
        		cnt++;
        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")));    
        		driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();
		        System.out.println(cnt + "번 url : " + driver.getCurrentUrl());
				re.add(driver.getCurrentUrl());
            }//while   
        }//for
         
         
	    	 } catch (Exception e) {
	     		e.printStackTrace();
		
	     }  finally {
	         driver.close();
	         //driver.quit();
	     }
	     return re;
    }
}
