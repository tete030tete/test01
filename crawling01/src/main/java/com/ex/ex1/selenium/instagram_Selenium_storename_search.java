package com.ex.ex1.selenium;

import java.sql.Time;
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

public class instagram_Selenium_storename_search {

	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_storename_search.class);
	
	
	public ArrayList<Object> crawl(WebDriver driver, String storename) throws InterruptedException {
	
		ArrayList<Object> re = new ArrayList<Object>();
		String base_url = "https://www.instagram.com/" + storename; 

		
		driver.get(base_url);
	
		
		By by = By.cssSelector(".FFVAD");
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    
		try {
			
			try {
				wait.until(ExpectedConditions.numberOfElementsToBe(by, 20));
            } catch(TimeoutException e) {
            	logger.error("TimeoutException in <instagram_Selenium_storename_search crawl>");
                Thread.sleep(10000);
            }
			
			//함수 캐스팅으로 자바 스크립트 executor
			JavascriptExecutor js =(JavascriptExecutor)driver;
			Document doc = Jsoup.parse(driver.getPageSource()); 
			
			
			System.out.println("======================================================");
			System.out.println("HTML TITLE : " + doc.title()); 
			
			//가게 대표 사진
			Element storeimg = doc.selectFirst("._6q-tv");
			String storehref = storeimg.outerHtml();
			System.out.println("대표사진 : " + storehref);
			re.add(storehref);
			
			     
			//가게 아이디
			Element storeid2 = doc.selectFirst("._7UhW9.fKFbl.yUEEX.KV-D4.fDxYl");
			String storeid = storeid2.text();
			System.out.println("가게아이디 : " + storeid);
			re.add(storeid);
			
			//가게 팔로워
			Element storefollower2 = doc.selectFirst(".g47SY");
			String storefollower = storefollower2.text();
			System.out.println("가게 팔로워 : " + storefollower);
			re.add(storefollower);
			
			 
			//가게 프로필
			Element storeprofile2 = doc.selectFirst(".-vDIg > span");
			String storeprofile = storeprofile2.text();
			System.out.println("가게프로필 : " + storeprofile);
			re.add(storeprofile);
			
			
			//가게 포스트
			Elements posts = doc.select(".FFVAD"); 
			int i = 1;
			ArrayList<Object> postlist = new ArrayList<Object>();
			for (Element element : posts) {
				element.attr("style", "object-fit: cover;");
				String posthtml = element.outerHtml();
				
				System.out.println("포스트 : "+ i + posthtml);
				i++;
				postlist.add(posthtml);
			}
			re.add(postlist);
		
			
			//가게 포스트 href 
			Elements posts2 = doc.select(".v1Nh3.kIKUG._bz0w > a"); 
			ArrayList<Object> postlist2 = new ArrayList<Object>();
			for (Element element2 : posts2) {
				String ahref = element2.attr("href");
				System.out.println("포스트 ahref : " + ahref);    				
				postlist2.add(ahref);
			}
			re.add(postlist2);
		
			System.out.println("======================================================");

		
		}catch (NoSuchElementException one_more_try) {

            try {
            	 wait.until(ExpectedConditions.numberOfElementsToBe(by,20));
            	//함수 캐스팅으로 자바 스크립트 executor
     			JavascriptExecutor js =(JavascriptExecutor)driver;
     			Document doc = Jsoup.parse(driver.getPageSource()); 
     			
    			
    			
    			System.out.println("======================================================");
    			System.out.println("HTML TITLE : " + doc.title()); 
    			
    			//가게 대표 사진
    			Element storeimg = doc.selectFirst("._6q-tv");
    			String storehref = storeimg.outerHtml();
    			System.out.println("대표사진 : " + storehref);
    			re.add(storehref);
    			
    			     
    			//가게 아이디
    			Element storeid2 = doc.selectFirst("._7UhW9.fKFbl.yUEEX.KV-D4.fDxYl");
    			String storeid = storeid2.text();
    			System.out.println("가게아이디 : " + storeid);
    			re.add(storeid);
    			
    			//가게 팔로워
    			Element storefollower2 = doc.selectFirst(".g47SY");
    			String storefollower = storefollower2.text();
    			System.out.println("가게 팔로워 : " + storefollower);
    			re.add(storefollower);
    			
    			 
    			//가게 프로필
    			Element storeprofile2 = doc.selectFirst(".-vDIg > span");
    			String storeprofile = storeprofile2.text();
    			System.out.println("가게프로필 : " + storeprofile);
    			re.add(storeprofile);
    			
    			//가게 포스트 이미지 -- 24개
    			Elements posts = doc.select(".FFVAD"); 
    			ArrayList<Object> postlist = new ArrayList<Object>();
    			for (Element element : posts) {
    				element.attr("style", "object-fit: cover;");
    				String posthtml = element.outerHtml();
    				System.out.println("포스트 : " + posthtml);    				
    				postlist.add(posthtml);
    			}
    			re.add(postlist);
    			
    			//가게 포스트 href 
    			Elements posts2 = doc.select(".v1Nh3.kIKUG._bz0w > a"); 
    			ArrayList<Object> postlist2 = new ArrayList<Object>();
    			for (Element element2 : posts2) {
    				String ahref = element2.attr("href");
    				System.out.println("포스트 ahref : " + ahref);    				
    				postlist2.add(ahref);
    			}
    			re.add(postlist2);
    		
    			System.out.println("======================================================");
                
            } catch (NoSuchElementException no_date_exist) {
                re = new ArrayList<Object>();
            }
        } catch (NullPointerException e) {
            re = new ArrayList<Object>();
        }finally {
			driver.close();
		}
		return re;
	}
	
	
}
