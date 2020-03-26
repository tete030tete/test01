package com.ex.ex1.selenium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class instagram_Selenium_tag {

	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_tag.class);
	
	public ArrayList<String> crawl(WebDriver driver,String base_url) throws InterruptedException {
		
		ArrayList<String> re = new ArrayList<String>();
		driver.get(base_url);
		By by = By.cssSelector(".LFGs8.xil3i");
        WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch(TimeoutException e) {
            	logger.error("TimeoutException in <instagram_Selenium_tag crawl>");
                Thread.sleep(10000);
            }
			
			
			//함수 캐스팅으로 자바 스크립트 executor
			JavascriptExecutor js =(JavascriptExecutor)driver;
			Document doc = Jsoup.parse(driver.getPageSource()); 
			System.out.println("======================================================");
			System.out.println("HTML TITLE : " + doc.title()); 
			
			
			Elements tags = doc.select(".LFGs8.xil3i"); //동명동맛집 관련태그들의 a 태그
			for (Element element : tags) {
				String text = element.text().substring(1); //----동명동핫플
				//String text = element.attr("href"); //----/explore/tags/동명동핫플/
				//String text = element.outerHtml(); // atag html
				System.out.println("태그 : " + text);
				re.add(text);
			}
			System.out.println("======================================================");

		} catch (NoSuchElementException one_more_try) {

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
         	   
    			JavascriptExecutor js =(JavascriptExecutor)driver;
    			Document doc = Jsoup.parse(driver.getPageSource()); 
    			System.out.println("======================================================");
    			System.out.println("HTML TITLE : " + doc.title()); 
    			
    			Elements tags = doc.select(".LFGs8.xil3i"); //동명동맛집 관련태그들의 a 태그
    			for (Element element : tags) {
    				String text = element.text().substring(1); //----동명동핫플
    				System.out.println("태그 : " + text);
    				re.add(text);
    			}
    		
    			System.out.println("======================================================");
                
            } catch (NoSuchElementException no_date_exist) {
                re = new ArrayList<String>();
            }
        } catch (NullPointerException e) {
            re = new ArrayList<String>();
        }finally {
			driver.close();
		}
		return re;
	}
	
	
}
