package selenium_Test;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1 {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty ( "webdriver.firefox.bin" , "D:/Program Files/Mozilla Firefox/firefox.exe" );
		driver = new FirefoxDriver();
	}

	@Test
	public void test_case2() throws Exception {
		driver.get("http://www.google.com.hk");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("hello Selenium!");
		element.submit();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Page title is: " + driver.getTitle());
		driver.quit();
	}
}