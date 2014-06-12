package selenium_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Test3 {
	WebDriver driver;

	@Test
	public void f() {
		driver = new FirefoxDriver();
		driver.get("http://www.google.com.hk");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("hello Selenium!");
		element.submit();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty ( "webdriver.firefox.bin" , "D:/Program Files/Mozilla Firefox/firefox.exe" );
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Page title is: " + driver.getTitle());
		driver.quit();
	}

}
