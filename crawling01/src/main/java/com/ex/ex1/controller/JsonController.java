package com.ex.ex1.controller;


import org.openqa.selenium.WebDriver;
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
import com.ex.ex1.selenium.instagram_search_list;


@Controller
public class JsonController {

	@Autowired
	private IstoreDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ChromeDriverBeans.class);
	private WebDriver driver = ctx.getBean("setupChromeDriver", WebDriver.class);
	//private WebDriver driver = ctx.getBean("getDriver", WebDriver.class);

	
	@RequestMapping(value="/crawling_search_list",method=RequestMethod.POST)
	public String crawling_tag(String search,Model model) throws Exception{
		logger.info("검색어 연관 리스트");
		instagram_search_list ins = new instagram_search_list();
		ins.crawl(driver,search);
		//model.addAttribute("list", list);
		return "tagsearch";
	}
}
