package com.ex.ex1.selenium;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_post_click {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_post_click.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

	
    public ArrayList<String> post_click(String base_url) throws ParseException, InterruptedException {
    	ArrayList<String> re = new ArrayList<String>();
    	
    	System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
        driver = new ChromeDriver();        
    	driver.get(base_url);
        
        WebDriverWait wait = new WebDriverWait(driver, 30);
        
        try {
     
   			Document doc = Jsoup.parse(driver.getPageSource());
            System.out.println("======================================================");
            By by;
            By by_img = By.cssSelector(".FFVAD");
            WebElement img =  driver.findElement(by_img);
            Element post_href_ele = doc.selectFirst(".v1Nh3.kIKUG._bz0w > a");
            String post_href = post_href_ele.attr("href");
            System.out.println("post_href : " + post_href);
            String url = "https://www.instagram.com" + post_href;
            re.add(url);
            
            JavascriptExecutor js =(JavascriptExecutor)driver;                       
            js.executeScript("arguments[0].click();", img);            
            Thread.sleep(1000); 
            
            while(true) {
		        by = By.cssSelector("._65Bje.coreSpriteRightPaginationArrow");
		        if(by == null) break;
		        WebElement search =  driver.findElement(by);
				js.executeScript("arguments[0].click();", search);
				Thread.sleep(1000); 
				url = driver.getCurrentUrl();
				System.out.println("url : " + url);
				re.add(url);
            }
   		
   			System.out.println("======================================================");
   			
        } catch (NoSuchElementException one_more_try) {
        		one_more_try.printStackTrace();
            try {
               
            } catch (NoSuchElementException no_date_exist) {
            	re = new ArrayList<String>();
            }

        } catch (NullPointerException e) {
        	e.printStackTrace();
        	re = new ArrayList<String>();

        } finally {
        
            driver.close();
        }
        return re;
    }

}
