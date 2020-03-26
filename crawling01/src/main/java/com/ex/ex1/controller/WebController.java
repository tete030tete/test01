package com.ex.ex1.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.entity.ContentType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ex.ex1.config.ChromeDriverBeans;
import com.ex.ex1.dao.IstoreDAO;
import com.ex.ex1.selenium.getrend_crawl_istore;
import com.ex.ex1.selenium.instagram_Selenium_post;
import com.ex.ex1.selenium.instagram_Selenium_storename_search;
import com.ex.ex1.selenium.instagram_Selenium_tag;
import com.ex.ex1.selenium.instagram_Selenium_tag_search;
import com.ex.ex1.selenium.instagram_selenium_date;
import com.ex.ex1.selenium.instagram_selenium_post_click;
import com.ex.ex1.selenium.instagram_selenium_post_list;
import com.ex.ex1.selenium.instagram_selenium_post_location;
import com.ex.ex1.selenium.instagram_selenium_setsearch;
import com.ex.ex1.vo.Istores;
import com.ex.ex1.vo.Istores_info;


@Controller
public class WebController {

	@Autowired
	private IstoreDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ChromeDriverBeans.class);
	private WebDriver driver = ctx.getBean("setupChromeDriver", WebDriver.class);
	//private WebDriver driver = ctx.getBean("getDriver", WebDriver.class);

	
	@RequestMapping(value="/post_list_five",method=RequestMethod.GET)
	public String post_list_five(Model model) throws Exception{
		logger.info(" 가게 포스트 얻기");
		ArrayList<String> store_ids = new ArrayList<String>();
		int i = 0;
		instagram_selenium_post_list ins = new instagram_selenium_post_list();
		while(i < 10) {
			//hellol__official/_randori
			ins.post_list("hellol__official");
			i++;
		}		
		return "home";
	}
	
	
	
	
	@RequestMapping(value="/crawling_tag",method=RequestMethod.POST)
	public String crawling_tag(String base_url,Model model) throws Exception{
		logger.info("관련 태그");
		instagram_Selenium_tag ins = new instagram_Selenium_tag();
		ArrayList<String> taglist  = ins.crawl(driver,base_url);
		model.addAttribute("taglist", taglist);
		return "tagsearch";
	}
	
	@RequestMapping(value="/crawling_tag_search",method=RequestMethod.POST)
	public String crawling_tag_search(String tag, Model model) throws Exception{
		logger.info("관련 태그");
		instagram_Selenium_tag_search ins = new instagram_Selenium_tag_search();
		ArrayList<String> taglist  = ins.crawl(driver, tag);
		model.addAttribute("tag", tag);
		model.addAttribute("taglist", taglist);
		return "tagsearch";
	}
	
	@RequestMapping(value="/crawling_post",method=RequestMethod.POST)
	public String crawling_post(String base_url, Model model) throws Exception{
		/*logger.info("포스트 태그");
		instagram_Selenium_post post = new instagram_Selenium_post();
		ArrayList<String> taglist  = post.crawl(driver, base_url); //[#조대맛집,...]
		model.addAttribute("taglist", taglist);*/
		
		logger.info("포스트 날짜");
		instagram_selenium_date date = new instagram_selenium_date();
		Date postdate = date.getDate(driver,base_url); //Thu Jan 01 09:00:00 KST 1970
		model.addAttribute("postdate", postdate);
		return "post";
	}
	
	
	
	@RequestMapping(value="/crawling_storename_search",method=RequestMethod.POST)
	public String crawling_storename_search(String storename, Model model) throws Exception{
		logger.info("관련 태그");
		instagram_Selenium_storename_search ins = new instagram_Selenium_storename_search();
		ArrayList<Object> taglist  = ins.crawl(driver, storename);
		model.addAttribute("storename", storename);
		model.addAttribute("taglist", taglist);
		return "tagsearch";
	}
	
	
	@RequestMapping(value="/crawling_storename_getPlacetag",method=RequestMethod.POST)
	public String crawling_storename_getPlacetag(String base_url, Model model) throws Exception{
		logger.info("포스트 위치");
		
		instagram_selenium_post_location ins = new instagram_selenium_post_location();
		String placetag  = ins.crawl(driver, base_url);
		model.addAttribute("placetag", placetag);
		
		return "placetag";
	}
	
	
     @RequestMapping(value="/ByPixel",method=RequestMethod.GET)
	 public String ByPixel() throws Exception {
         System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
         driver = new ChromeDriver();
      	 String loginPage = "https://www.instagram.com/accounts/login/?next=%2Fhellol__official%2F&source=desktop_nav";
      	 driver.get(loginPage);
      	 WebDriverWait wait = new WebDriverWait(driver, 5);
      	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2hvTZ.pexuQ.zyHYP")));    
      	 WebElement loginId = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(0);
      	 loginId.clear();
      	 loginId.sendKeys("tete030tete@gmail.com");
      	 WebElement loginPwd = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP")).get(1);
      	 loginPwd.clear();
      	 loginPwd.sendKeys("tete030");
      	 driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
      	
        
        //driver.get("https://search.shopping.naver.com/best100v2/main.nhn");
        for(int i = 1; i < 5 ; i++) {
        	Thread.sleep(500);
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollTo(0, 1000)");
        	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
        
    	JavascriptExecutor js =(JavascriptExecutor)driver;
		Document doc = Jsoup.parse(driver.getPageSource()); 
		
		//동명동맛집 관련태그들의 a 태그
		Thread.sleep(5000);
		Elements hrefs = doc.select(".v1Nh3.kIKUG._bz0w > a");
		int cnt = 1;
		for (Element element : hrefs) {
			String href = element.attr("href");
			System.out.println(cnt + "번째 href : " + href);
			cnt++;
		}
        
        driver.close();
        return "home";
        //js.executeScript("window.scrollTo(0, 100000000)");
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
     
     
  
     
     
     
     
 	@RequestMapping(value="/insert_istore",method=RequestMethod.GET)
 	public String crawling_storename_search() throws Exception{
 		logger.info("insert istore 시작");
 		String insta_id = "hellol__official";
 		getrend_crawl_istore ins = new getrend_crawl_istore();
 		HashMap<String, Object> list  = ins.crawl(driver, insta_id);
 		logger.info("list {}",list);
 		return "home";
 	}
	
	
	@RequestMapping(value="/crawling_setsearch",method=RequestMethod.GET)
	public String crawling_setsearch(Model model) throws Exception{
		logger.info("검색어 보내기");
		instagram_selenium_setsearch ins = new instagram_selenium_setsearch();
		ins.setSearch();
		return "home";
	}
	
	
	
	@RequestMapping(value="/post_click",method=RequestMethod.POST)
	public String post_click(String base_url, Model model) throws Exception{
		logger.info("검색어 보내기");
		instagram_selenium_post_click ins = new instagram_selenium_post_click();
		ins.post_click(base_url);
		return "home";
	}
	
	
	
	
	   @RequestMapping(value="/followerCount",method=RequestMethod.GET)
		 public String followerCount() {
		   long beforeTime = System.currentTimeMillis(); 
	      
		   System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
	         driver = new ChromeDriver();
	      	try {
	      	     for(int i = 0; i < 100 ; i++) {
			         String loginPage = "https://www.instagram.com/hellol__official/";
			      	 driver.get(loginPage);
			      	 WebDriverWait wait = new WebDriverWait(driver, 5);
			      	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".g47SY"))); 
			      	 
			      	 Document doc = Jsoup.parse(driver.getPageSource()); 
			      	 Element ele = doc.select(".g47SY").get(1);
			      	 String followerCnt = ele.attr("title");
			         System.out.println("followerCnt : " + followerCnt);
		         }
		        
		        driver.close();
		        
		        long afterTime = System.currentTimeMillis(); 
				long secDiffTime = (afterTime - beforeTime)/1000; 
				System.out.println("시간차이(m) : "+secDiffTime);
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	    
	        return "home";
	    }
	     

	
	
}
