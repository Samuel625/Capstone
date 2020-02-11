package com.capstone.capstone1;


import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverTests {

	static WebDriver driver;
	
    @BeforeClass
	public static void openBrowser() {
   
    	//System.setProperty("webdriver.ie.driver", "C:\\ws\\drivers\\IEDriverServer.exe");
        //driver=new InternetExplorerDriver();
    	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();		 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
    }
    
	
	@Test
	public void login() throws InterruptedException {
		driver.get("http://localhost:3500/capstone/login");
		driver.findElement(By.id("user")).sendKeys("S@gmail.com");
		//Thread.sleep(3000);
		driver.findElement(By.id("pwd")).sendKeys("1234");
	    //Thread.sleep(3000);
	    driver.findElement(By.id("submiter")).click(); 
	    Thread.sleep(3000);
	    driver.getPageSource().contains("Profile");
	}
	
	@Test
	public void google() throws InterruptedException {
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
	}
	
	@AfterClass
	public static void closeBrowser() {		
		driver.quit();
	}
	
	
	
}

