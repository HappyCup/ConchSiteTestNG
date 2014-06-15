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

public class RegisterTest {

	private String datafile="data/register.xls";
	private String objectfile="data/uimap.xml";
	
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private AutoObjectFactory objects;

	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = WebDriverFactory.getFirefoxDriver();
		baseUrl = WebDriverFactory.getBaseUrl();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		objects=new AutoObjectFactory(objectfile);
	}

	@DataProvider(name="register")
	public Object[ ][ ] Register_data()
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
	
	@Test(dataProvider="register")
	public void test1(String username,String mail,String password,String rpassword,String expectedName,
			String expectedMail,String expectedPassword,String expectedRP,String flag) throws Exception {
		driver.get(baseUrl + "/");
		objects.fetchWebElement("RegisterLink", driver).click();
		
		objects.fetchWebElement("RegisterName", driver).clear();
		objects.fetchWebElement("RegisterName", driver).sendKeys(username);
		
		objects.fetchWebElement("RegisterEmail", driver).clear();
		objects.fetchWebElement("RegisterEmail", driver).sendKeys(mail);
		
		objects.fetchWebElement("RegisterPassword", driver).clear();
		objects.fetchWebElement("RegisterPassword", driver).sendKeys(password);
		
		objects.fetchWebElement("RegisterRPassword", driver).clear();
		objects.fetchWebElement("RegisterRPassword", driver).sendKeys(rpassword);
		
		objects.fetchWebElement("RegisterSubmit", driver).click();
		Thread.sleep(100);
		if(flag.equals("t")){
			String LinkTest = objects.fetchWebElement("mynickname", driver).getText().trim();
			assertEquals(LinkTest,expectedName);
		}
		else{
			String nameExist = objects.fetchWebElement("NameExist", driver).getText().trim();
			String mailError = objects.fetchWebElement("MailFormatError", driver).getText().trim();
			String pwError = objects.fetchWebElement("PwError", driver).getText().trim();
			String rpwError = objects.fetchWebElement("RPwError", driver).getText().trim();
			assertEquals(nameExist,expectedName);
			assertEquals(mailError,expectedMail);
			assertEquals(pwError,expectedPassword);
			assertEquals(rpwError,expectedRP);
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
