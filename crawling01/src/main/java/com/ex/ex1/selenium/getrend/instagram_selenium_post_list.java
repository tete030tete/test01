package com.ex.ex1.selenium.getrend;

import java.awt.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_post_list {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_post_list.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

	
    public ArrayList<String> post_list(String url) {
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
	       /*	 String loginPage = "https://www.instagram.com/accounts/login/?next=%2F" + instaid + "%2F&source=desktop_nav";
	       	 
	       	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
	       	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
	       	 loginId.clear();
	       	 loginId.sendKeys("tete030tete@gmail.com");
	       	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
	       	 loginPwd.clear();
	       	 loginPwd.sendKeys("exoexo10");
	       	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();*/
        
        	driver.get(url);
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
            
            while(cnt < 1000) {
        		cnt++;
        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")));    
        		driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();
		        System.out.println(cnt + "번 url : " + driver.getCurrentUrl());
				re.add(driver.getCurrentUrl());
				int ing = 30;
				if(cnt % ing == 0) {
					//driver.findElement(By.cssSelector(".ITLxV.coreSpriteLeftPaginationArrow ")).click(); //왼
					//driver.findElement(By.cssSelector("._65Bje.coreSpriteRightPaginationArrow")).click();//오
					ing += 30;
					//닫기
					driver.findElement(By.cssSelector(".Igw0E.IwRSH.eGOV_._4EzTm.BI4qX.qJPeX.fm1AK.TxciK.yiMZG")).click();//닫기
					//열기
					Thread.sleep(1000);
					//driver.findElements(By.cssSelector(".FFVAD")).get(cnt).click();
			        js.executeScript("arguments[0].click();", driver.findElements(By.cssSelector(".FFVAD")).get(cnt));      
    				}
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
