package com.ex.ex1.selenium;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class instagram_selenium_date {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_selenium_date.class);

	//WebDriver
	private WebDriver driver;
	private WebElement element;

	
    public Date getDate(WebDriver driver, String base_url) throws ParseException {
        this.driver = driver;
        driver.get(base_url);
        String realdate = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy");
        By by = By.cssSelector("body > div._2dDPU.CkGkG > div.zZYga > div > article > div.eo2As > div.k_Q0X.NnvRN > a > time");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch(TimeoutException e) {
            	logger.error("TimeoutException in <getDate>");
                Thread.sleep(10000);
            }
            
            this.element = driver.findElement(by);

            realdate = element.getAttribute("title");

        } catch (NoSuchElementException one_more_try) {

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                this.element = driver.findElement(by);
                realdate = element.getAttribute("title");
            } catch (NoSuchElementException no_date_exist) {
                realdate = "";
            }

        } catch (NullPointerException e) {

            realdate = "";

        } finally {
        
            if (!realdate.equals("")) {
                realdate = getMonth(realdate);
                java.util.Date utilDate = simpleDateFormat.parse(realdate);
                Date settingDate = new Date(utilDate.getTime());
                return settingDate;
            }
            else {
                return new Date(0);
            }
            
        }
    }

    private String getMonth(String date) {
    
        switch (date.substring(0, 3)) {
            case "Jan" :
                date = date.replace("Jan", "1");
                break;

            case "Feb" :
                date = date.replace("Feb", "2");
                break;

            case "Mar" :
                date = date.replace("Mar", "3");
                break;

            case "Apr" :
                date = date.replace("Apr", "4");
                break;

            case "May" :
                date = date.replace("May", "5");
                break;

            case "Jun" :
                date = date.replace("Jun", "6");
                break;

            case "Jul" :
                date = date.replace("Jul", "7");
                break;

            case "Aug" :
                date = date.replace("Aug", "8");
                break;

            case "Sep" :
                date = date.replace("Sep", "9");
                break;

            case "Oct" :
                date = date.replace("Oct", "10");
                break;

            case "Nov" :
                date = date.replace("Nov", "11");
                break;

            case "Dec" :
                date = date.replace("Dec", "12");
                break;
        }

        return date;
    }
}
