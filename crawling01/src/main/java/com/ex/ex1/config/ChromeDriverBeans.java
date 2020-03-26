package com.ex.ex1.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.*;

//XML이 방식이 아닌 JAVA의 형식으로 beans.xml을 대체한다.
@Configuration
public class ChromeDriverBeans {
	
	private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(ChromeDriverBeans.class);
    private static final String CHROME_DRIVER_PATH = "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe";

    
    @Bean
    public WebDriver getDriver() {
        return driver;
       
    }

    @Bean
    public WebDriver setupChromeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1366,768");
        options.addArguments("--headless");
        options.setProxy(null);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("pageLoadStrategy", "none");

        try {
        	driver = new ChromeDriver(options);
        	
        	
        } catch (Exception e) {
            logger.error("### [driver error] msg: {}, cause: {}", e.getMessage(), e.getCause());
        }

        return driver;
    }
}
