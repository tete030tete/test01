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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_setsearch {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_setsearch.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

	
    public void setSearch() throws ParseException, InterruptedException {
    	 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
         driver = new ChromeDriver();
    	ArrayList<String> re = new ArrayList<String>();
        driver.get("https://www.instagram.com/");
        By by = By.cssSelector(".XTCLo.x3qfX");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        
        try {
     
         	JavascriptExecutor js =(JavascriptExecutor)driver;
   			Document doc = Jsoup.parse(driver.getPageSource());
            System.out.println("======================================================");
   			System.out.println("HTML TITLE : " + doc.title()); 
			WebElement search =  driver.findElement(by);
			
			search.clear();
			search.sendKeys("동명동");
			By by2 = By.cssSelector(".yCE8d");
			Elements results = doc.select(".yCE8d");
			int cnt = 1;
			for (Element result : results) {
				result.attr("style", "");
				String result_href = result.attr("href");
				System.out.println("result_href " + cnt + " : " + result_href);
				re.add(result_href);
			}
			
			wait.until(ExpectedConditions.elementToBeClickable(by2)); 
			Thread.sleep(5000);
	   			
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
    }

}
