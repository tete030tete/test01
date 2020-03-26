package com.ex.ex1.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.*;

//XML이 방식이 아닌 JAVA의 형식으로 beans.xml을 대체한다. 
//Configuration을 클래스에 적용하고 @Bean을 해당 클래스의 메소드에 적용하면 @Autowired로 빈을 부를 수 있다.

@Configuration
public class JobBeans {
	
	/*
	@Bean
	public JobDetailFactoryBean searchLocationJobSchedule(){
		return BatchSettings.jobDetailFactoryBeanBuilder().job(searchLocation()).build();
	}
	
    @Bean
    public Job searchLocation(){
    	return jobBuilderFactory.get(JOB_NAME).start(getLatestCrawlDateStep()).next(setupInstaLocationStep()).next(setupRestaurantsInLocationStep()).build();
    }*/
    
 
    
    
}
