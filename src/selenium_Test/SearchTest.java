package selenium_Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import test_utils.AutoObjectFactory;
import test_utils.ExcelDataHelper;
import test_utils.WebDriverFactory;

public class SearchTest {

	private String datafile="data/search_test.xls";
	private String objectfile="data/uimap.xml";
	
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private AutoObjectFactory objects;
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = WebDriverFactory.getFirefoxDriver();
		baseUrl = "http://conch.aliapp.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		objects=new AutoObjectFactory(objectfile);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@DataProvider(name="search")
	public Object[ ][ ] Search_data()
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
	
	@Test(dataProvider="search")
	public void test1(String search_key,String flag,String excepted) throws Exception {
		driver.get(baseUrl);
		objects.fetchWebElement("SearchInput", driver).click();
		objects.fetchWebElement("SearchInput", driver).clear();
		objects.fetchWebElement("SearchInput", driver).sendKeys(search_key);
		objects.fetchWebElement("SearchButton", driver).click();
		Thread.sleep(100);
		if(flag.equals("n")){
			String fieldTest = objects.fetchWebElement("SearchInput", driver).getAttribute("value").trim();
			System.out.println(fieldTest);
			assertEquals(fieldTest,excepted);
		}
		else{
			String fieldTest = objects.fetchWebElement("SearchNotice", driver).getText().trim();
			assertTrue(fieldTest.startsWith(excepted));
		}
	}
}
