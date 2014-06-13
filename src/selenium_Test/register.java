package selenium_Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class register {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://conch.aliapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegister() throws Exception {
    driver.get(baseUrl + "/hailuo_registe.jsp");
    driver.findElement(By.id("uUsername")).clear();
    driver.findElement(By.id("uUsername")).sendKeys("godlike");
    driver.findElement(By.id("uEmail")).clear();
    driver.findElement(By.id("uEmail")).sendKeys("123456@qq.com");
    driver.findElement(By.id("uPassword")).clear();
    driver.findElement(By.id("uPassword")).sendKeys("123456");
    driver.findElement(By.id("uRepassword")).clear();
    driver.findElement(By.id("uRepassword")).sendKeys("123456");
    driver.findElement(By.id("uUsername")).clear();
    driver.findElement(By.id("uUsername")).sendKeys("godlikes");
    driver.findElement(By.id("uSubmitBtn")).click();
    driver.findElement(By.id("uUsername")).clear();
    driver.findElement(By.id("uUsername")).sendKeys("godlikess");
    driver.findElement(By.id("uSubmitBtn")).click();
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
}
