package selenium_Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.DefaultSelenium;

public class Test2 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty ( "webdriver.firefox.bin" , "D:/Program Files/Mozilla Firefox/firefox.exe" );
		driver = new FirefoxDriver();
		baseUrl = "http://www.baidu.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("kw")).clear();
		driver.findElement(By.id("kw")).sendKeys("ฤ๚บร");
		driver.findElement(By.id("su")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
	
	public static void main(String[] args)

	{

	        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://www.baidu.com/");

	       selenium.start();

	       selenium.open("/");

	       selenium.type("id=kw1", "selenium");

	       selenium.click("id=su1");

	       System.out.println("Page title is: " + selenium.getTitle());

	       selenium.stop();

	 }
}
