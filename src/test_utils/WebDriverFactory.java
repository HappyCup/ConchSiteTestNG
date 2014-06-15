package test_utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {

	private static final String browserProperty = "data/brower.properties";
	
	static{
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(browserProperty));
			Properties p = new Properties();
			p.load(in);
			for(Entry<Object, Object> e : p.entrySet()){
				System.setProperty ( e.getKey().toString() , e.getValue().toString() );
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private WebDriverFactory(){}
	
	public static WebDriver getFirefoxDriver(){
		return new FirefoxDriver();
	}
	public static WebDriver getChromeDriver(){
		return new ChromeDriver();
	}
	public static WebDriver getIEDriver(){
		return new InternetExplorerDriver();
	}
	
	public static String getBaseUrl(){
		return System.getProperty("baseUrl");
	}
}
