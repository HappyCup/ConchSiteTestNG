package selenium_Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test_utils.WebDriverFactory;

public class PlayTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;

  
  @BeforeMethod
  public void setUp() throws Exception {
	  driver = WebDriverFactory.getFirefoxDriver();
	  baseUrl = "http://conch.aliapp.com";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test1() throws Exception {
    driver.get(baseUrl + "/playPage/playPage.htm");
    driver.findElement(By.linkText("play")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("pause")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("stop")).click();
  }
  
  @Test
  public void test2() throws Exception {
    driver.get(baseUrl + "/playPage/playPage.htm");
    driver.findElement(By.linkText("play")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("pause")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("play")).click();
  }

  @Test
  public void test3() throws Exception {
    driver.get(baseUrl + "/playPage/playPage.htm");
    driver.findElement(By.linkText("play")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("stop")).click();
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
