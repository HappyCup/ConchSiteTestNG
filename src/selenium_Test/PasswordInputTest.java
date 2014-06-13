package selenium_Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import test_utils.AutoObjectFactory;
import test_utils.ExcelDataHelper;
import test_utils.WebDriverFactory;

public class PasswordInputTest {

	private String datafile="data/pw.xls";
	private String objectfile="data/uimap.xml";
	
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private AutoObjectFactory objects;

	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = WebDriverFactory.getFirefoxDriver();
		baseUrl = "http://conch.aliapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		objects=new AutoObjectFactory(objectfile);
	}

	@DataProvider(name="pwValidate")
	public Object[ ][ ] Login_data()
	{
		try {
			InputStream is = new FileInputStream(datafile);
			ExcelDataHelper excelReader = new ExcelDataHelper();
			return excelReader.readContent(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Test(dataProvider="pwValidate")
	public void test1(String password,String flag,String excepted) throws Exception {
		driver.get(baseUrl + "/");
		objects.fetchWebElement("PwInputLink", driver).click();
		
		objects.fetchWebElement("PasswordInput", driver).clear();
		objects.fetchWebElement("PasswordInput", driver).sendKeys(password);
		
		objects.fetchWebElement("PasswordSubmit", driver).click();
		Thread.sleep(100);
		if(flag.equals("t")){
			String LinkTest = objects.fetchWebElement("PwRightNotice", driver).getText().trim();
			
			assertEquals(LinkTest,excepted);
		}
		else if(flag.equals("s")){
			String inputfield = objects.fetchWebElement("PasswordInput", driver).getAttribute("value");
			
			assertEquals(inputfield,excepted);
		}
		else{
			String LinkTest = objects.fetchWebElement("PwErrorNotice", driver).getText().trim();
			assertEquals(LinkTest,excepted);
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
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
}
