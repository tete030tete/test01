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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_post_location {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_post_location.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

    
    public String crawl(WebDriver driver, String base_url) throws InterruptedException {
        this.driver = driver;
        driver.get(base_url);
        String placetag = "";
        By by = By.cssSelector(".O4GlU");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));            
           	/*JavascriptExecutor js =(JavascriptExecutor)driver;
   			Document doc = Jsoup.parse(driver.getPageSource()); 
   			System.out.println("======================================================");
   			System.out.println("HTML TITLE : " + doc.title()); 
   		    Element placetag2 = doc.selectFirst(".O4GlU"); //tag
   			placetag = placetag2.text();*/
            
            placetag = driver.findElement(by).getText();
   			
   			
   			System.out.println("placetag : " + placetag);
            
        } catch(NoSuchElementException
                | TimeoutException one_more_try) {
        		logger.error("TimeoutException in <instagram_selenium_post_location crawl>");
        		Thread.sleep(10000);
            try {
            		
                by = By.xpath("/html/body/div[3]/div[2]/div/article/header/div[2]/div[2]/div[2]/a");
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));            	
                
                /*JavascriptExecutor js =(JavascriptExecutor)driver;
       			Document doc = Jsoup.parse(driver.getPageSource()); 
       			Element placetag2 = doc.selectFirst(".O4GlU"); //tag
       			placetag = placetag2.text();*/
                String like = driver.findElement(By.cssSelector(".sqdOP.yWX7d._8A5w5 > span")).getText();
                System.out.println("like : " + like);
                placetag = driver.findElement(by).getText();
       			
       			System.out.println("placetag : " + placetag);
            } catch(Exception e) {
                placetag = "";
            }

        } catch(NullPointerException e) {
            placetag = "";
        }
        
        return placetag;
    }
    

}
