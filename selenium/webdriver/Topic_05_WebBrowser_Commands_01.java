package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_05_WebBrowser_Commands_01 {
	WebDriver driver;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// tương tác với browser sẽ thông qua biến WebDriver driver
		// tương tác với Element sẽ thông qua biến WebElement Element
	}

	@Test
	public void TC_01_() {
		// >=2: nó sẽ đóng tab window mà nó đang đứng
	driver.close(); //*
	
	// không quan tâm bao nhiêu tab/window
	driver.quit(); //**
	 
	// Mở ra 1 URL nào đó
	  driver.get("https://www.facebook.com/"); //**
	  
	  // Click vào link: Tiếng Việt
	  // Trả về URL của page hiện tại. Tại sao ở trên mở ra ở dưới lại trả về làm gì?
	  Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
	  
	  // Trả về Source Code HTML của page hiện tại
	  // Verify tương đối
	  Assert.assertTrue( driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn."));
	  
	  // Trả về title của page hiện tại
	  driver.getTitle();
	  //"Facebook - Đăng nhập hoặc đăng ký"
	  Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
	  
	  // lấy ra được ID của Window/Tab mà driver đang đứng
	 String loginWindowID =  driver.getWindowHandle(); //*
	 
	 // Lấy ra ID của tất cả ID/ các tab
	 driver.getWindowHandles();
	 Set<String> allIDs = driver.getWindowHandles(); //*
	 
	 // Cookies/ cache
	 Options opt = driver.manage();
	 
	 // Login thành công, lưu lại
	 opt.getCookies(); //*
	 // testcase khác, set cookies vào lại không cần login
	 
	  opt.logs();
	  
	Timeouts time =  opt.timeouts();
	
	//khoảng thời gian đợi element xuất hiện trong vòng x giây
	time.implicitlyWait(15, TimeUnit.SECONDS);//**
	
	// khoảng thời gian đợi page load trong vòng x giây
	time.pageLoadTimeout(5, TimeUnit.SECONDS);
	
	// khoảng thời gian đợi script được thực thi xong
	time.setScriptTimeout(5, TimeUnit.SECONDS);
	
	Window win = opt.window();
	win.fullscreen();
	win.maximize(); //**
	
	// Test GUI: Font/Size/Color/Position/Location...
	win.getPosition();
	win.getSize();
	
	Navigation nav = driver.navigate();
	nav.back();
	nav.refresh();
	nav.forward();
	
	nav.to("https://www.facebook.com/");
	
	TargetLocator tar = driver.switchTo();
	tar.alert();//*
	tar.frame("");//*
	tar.window("");//*
	  
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}