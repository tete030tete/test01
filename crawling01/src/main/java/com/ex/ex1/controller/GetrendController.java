package com.ex.ex1.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ex.ex1.selenium.getrend.instagram_Selenium_header;
import com.ex.ex1.selenium.getrend.instagram_Selenium_location_post;
import com.ex.ex1.selenium.getrend.instagram_Selenium_store;
import com.ex.ex1.selenium.getrend.instagram_Selenium_store_post;
import com.ex.ex1.vo.Istores;
import com.ex.ex1.vo.Istores_info;


@Controller
public class GetrendController {

	private static final Logger logger = LoggerFactory.getLogger(GetrendController.class);

	

	@RequestMapping(value="/store",method=RequestMethod.GET)
	public String store(Model model) throws Exception{
		
		logger.info("가게 헤더 + 포스트 정보");
		long start = System.currentTimeMillis();
		instagram_Selenium_store ins = new instagram_Selenium_store();
		ArrayList<String> instaid = new ArrayList<String>();

		//6가게 성공 1 1 1 = 150초
		for(int i = 0; i < 6 ; i++) { 
		instaid.add("hellol__official");
		}
		HashMap<String, Object> map = ins.header(instaid);
		//가게 대표사진
		model.addAttribute("storehref", (String)map.get("storehref"));
		//가게 팔로워수
		model.addAttribute("storefollower", (String)map.get("storefollower"));
		//가게 포스트 url
		model.addAttribute("posturllist", map.get("posturllist"));
		//가게 포스트 좋아요
		model.addAttribute("postlikelist", map.get("postlikelist"));
		//가게 포스트 글
		model.addAttribute("postlist", map.get("postlist"));
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 ); //166초 1가게 당 25초
		return "home";
	}
	
	@RequestMapping(value="/store_header",method=RequestMethod.GET)
	public String store_header(Model model) throws Exception{
		
		logger.info("가게 헤더 정보");
		long start = System.currentTimeMillis();
		instagram_Selenium_header ins = new instagram_Selenium_header();
		ArrayList<String> instaid = new ArrayList<String>();

		//100 가게 134초
		for(int i = 0; i < 100 ; i++) { 
		instaid.add("hellol__official");
		}
		HashMap<String, Object> map = ins.header(instaid);
		//가게 대표사진
		model.addAttribute("storehref", (String)map.get("storehref"));
		//가게 팔로워수
		model.addAttribute("storefollower", (String)map.get("storefollower"));
		//가게 포스트 url
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 ); 
		return "home";
	}
	
	
	@RequestMapping(value="/store_post",method=RequestMethod.GET)
	public String store_post(HttpServletRequest request, Model model) throws Exception{
		
		logger.info("가게 포스트 정보");
		long start = System.currentTimeMillis();
		instagram_Selenium_store_post ins = new instagram_Selenium_store_post();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//로그인 시 차단 방지
		ArrayList<String> instaid = new ArrayList<String>();

		//9개 80
		for(int i = 0; i < 1 ; i++) { 
		  instaid.add("_randori");
		}
		
		map = ins.post_list(instaid);
		
		//가게 포스트 url
		model.addAttribute("posturllist", map.get("posturllist"));
		//가게 포스트 좋아요
		model.addAttribute("postlikelist", map.get("postlikelist"));
		//가게 포스트 글
		model.addAttribute("postlist", map.get("postlist"));
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
		return "home";
	}
	
	
	
	
	
	@RequestMapping(value="/post_list",method=RequestMethod.GET)
	public String post_list(Model model) throws Exception{
		logger.info("가게의 최신 포스트 30개 얻기");
		ArrayList<String> store_ids = new ArrayList<String>();
		instagram_selenium_post_list ins = new instagram_selenium_post_list();
		
		//가게 10개 정보 한번에 가져오기
		int i = 0;
		while(i < 10) {	
			ins.post_list("hellol__official");//hellol__official/_randori
			i++;
		}		
		return "home";
	}
	

	
	
	///망고 플레이트 가게 소개
	@RequestMapping(value="/mango_store",method=RequestMethod.GET)
	public String mango_store(Model model) throws Exception{
		 logger.info("mango_store ");
		 ArrayList<String> mango_store = new ArrayList<String>();
		 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 			 
		 WebDriver driver = new ChromeDriver();  
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 
		 for(int i = 0; i < 5 ; i++) {
			 driver.get("https://www.mangoplate.com/search/란도리");
			 
			 //첫 시도에서 팝업창 있으면 닫기
			 if(i == 0 && By.cssSelector(".dfp_ad_front_banner_wrap iframe") != null) {
				
				 WebElement iframe = driver.findElement(By.cssSelector(".dfp_ad_front_banner_wrap iframe"));
				 driver.switchTo().frame(iframe).findElement(By.cssSelector(".ad_block_btn")).click();
				 //driver.switchTo().defaultContent();
				 driver.switchTo().parentFrame();
			 }
			 
			 //게시물 중 첫번째 선택
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center-croping.lazy")));
			 driver.findElements(By.cssSelector(".center-croping.lazy")).get(0).click();		 
			 
			 //가게 설명 가져오기
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info.no_menu")));
			 List<WebElement> text =driver.findElements(By.cssSelector(".info.no_menu td"));
			 for (WebElement ele : text) {
				 System.out.println(ele.getText());			
			 }
		 
		 }
		 driver.close();

//주차공간, 휴일, 운영시간도 있음.		 
//		 광주광역시 서구 상무민주로32번길 3-22
//		 지번 광주시 서구 쌍촌동 1303-4
//		 062-371-0170
//		 카페 / 디저트
//		 만원 미만
//		 주차공간없음
//		 10:00 - 22:00
//		 일
		 
//란도리 - 주차공간, 휴일, 운영시간은 없음		 
//		 광주광역시 동구 동명로 45-1
//		 지번 광주시 동구 동명동 177-14
//		 062-232-1513
//		 정통 일식 / 일반 일식
//		 만원-2만원
		 
		 return "home";
	}

	
	@RequestMapping(value="/location_post",method=RequestMethod.GET)
	public String location_post(Model model) throws Exception{
		instagram_Selenium_location_post ins = new instagram_Selenium_location_post();
		
		//썸네일  + 인기 포스트 10개 좋아요, imgsrc
		ins.location_post("https://www.instagram.com/explore/locations/255999055/");
		return "home";
	}
	
	
	
    @RequestMapping(value="/like_and_img",method=RequestMethod.GET)
   	 public String like_and_img() {
       
     	
        return "home";
       }
   
	   
	     
	     @RequestMapping(value="/ByMouse",method=RequestMethod.GET)
	        public String ByMouse() throws Exception {
	        System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	         String loginPage = "https://www.instagram.com/explore/locations/1024535414/";
	         driver.get(loginPage);
	         WebDriverWait wait = new WebDriverWait(driver, 5);
	         JavascriptExecutor js =(JavascriptExecutor)driver;
	         Document doc = Jsoup.parse(driver.getPageSource()); 
	         
	         //mouseenter
	         Actions action = new Actions(driver);
	         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._9AhH0")));    
	         WebElement we = driver.findElement(By.cssSelector("._9AhH0"));
	         List<WebElement> list = driver.findElements(By.cssSelector("._9AhH0"));
	         for (int i = 0; i < list.size() ; i++) {
	             action.moveToElement(list.get(i)).perform();
	             Thread.sleep(5000);
	             wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".qn-0x")));
	             WebElement like_ele = driver.findElement(By.cssSelector(".qn-0x"));
	             String like_href = like_ele.getText();
//	             likehref.replaceAll(" ", "");
	             String likearray[] = like_href.split(" ");
	             String like = likearray[0];
	             System.out.println("like : " + like);
	        }
	          
	         //action.moveToElement(we);
	         //action.click().build().perform();
	         
	        driver.close();
	        return "home";
	       }
	         
	
}
