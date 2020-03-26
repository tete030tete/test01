package com.ex.ex1.selenium;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class getrend_crawl_istore {

	private static final Logger logger = LoggerFactory.getLogger(getrend_crawl_istore.class);
	
	
	public HashMap<String, Object> crawl(WebDriver driver, String storename) throws InterruptedException {
	
		HashMap<String, Object> re = new HashMap<String, Object>();
		String base_url = "https://www.instagram.com/" + storename; 

	
		By by = By.cssSelector(".FFVAD");
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    
		try {
			//함수 캐스팅으로 자바 스크립트 executor
			JavascriptExecutor js =(JavascriptExecutor)driver;
			driver.get(base_url);
			//js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			
			try {
				wait.until(ExpectedConditions.numberOfElementsToBe(by, 20));
            } catch(TimeoutException e) {
            	logger.error("TimeoutException in <instagram_Selenium_storename_search crawl>");
                Thread.sleep(10000);
            }
			
			Document doc = Jsoup.parse(driver.getPageSource()); 
			
			
			System.out.println("======================================================");
			System.out.println("HTML TITLE : " + doc.title()); 
			
			//가게 대표 사진
			Element storeimg = doc.selectFirst("._6q-tv");
			String profile_img = storeimg.outerHtml();
			System.out.println("profile_img : " + profile_img);
			re.put("profile_img",profile_img);
			
			//가게 프로필
			Element storeprofile2 = doc.selectFirst(".-vDIg > span");
			String storeprofile = storeprofile2.text();
			System.out.println("가게프로필 : " + storeprofile);
			
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
			//re.put("info_img",postlist);
		
			
			//가게 포스트 url 포함 a tag 전체
			Elements posts2 = doc.select(".v1Nh3.kIKUG._bz0w > a"); 
			ArrayList<Object> postlist2 = new ArrayList<Object>();
			for (Element element2 : posts2) {
				String ahref = element2.outerHtml();
				System.out.println("포스트 ahref : " + ahref);    				
				postlist2.add(ahref);
			}
			re.put("info_img",postlist2);
		
			System.out.println("======================================================");

		
		}catch (NoSuchElementException one_more_try) {

            try {
            	 wait.until(ExpectedConditions.numberOfElementsToBe(by,20));
     			Document doc = Jsoup.parse(driver.getPageSource()); 
     			
    			
    			
    			System.out.println("======================================================");
    			System.out.println("HTML TITLE : " + doc.title()); 
    			
    			//가게 대표 사진
    			Element storeimg = doc.selectFirst("._6q-tv");
    			String profile_img = storeimg.outerHtml();
    			System.out.println("profile_img : " + profile_img);
    			re.put("profile_img",profile_img);
    			
    			//가게 프로필
    			Element storeprofile2 = doc.selectFirst(".-vDIg > span");
    			String storeprofile = storeprofile2.text();
    			System.out.println("가게프로필 : " + storeprofile);
    			
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
    			//re.put("info_img",postlist);
    		
    			
    			//가게 포스트 url 포함 a tag 전체
    			Elements posts2 = doc.select(".v1Nh3.kIKUG._bz0w > a"); 
    			ArrayList<Object> postlist2 = new ArrayList<Object>();
    			for (Element element2 : posts2) {
    				String ahref = element2.outerHtml();
    				System.out.println("포스트 ahref : " + ahref);    				
    				postlist2.add(ahref);
    			}
    			re.put("info_img",postlist2);
    		
    			System.out.println("======================================================");
                
            } catch (NoSuchElementException no_date_exist) {
                re = new HashMap<String, Object>();
            }
        } catch (NullPointerException e) {
            re = new HashMap<String, Object>();
        }finally {
			driver.close();
		}
		return re;
	}


	
}
