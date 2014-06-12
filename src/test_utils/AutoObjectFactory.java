package test_utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class AutoObjectFactory {

	private String mappingfile;
	private List<ObjectItem> ObjectTreeData;
	private SAXParserFactory factory = SAXParserFactory.newInstance();
	private SAXParser parser;
	private SAXPraserHelper helperHandler = new SAXPraserHelper();
	
	public AutoObjectFactory(String mappingfile){
		this.mappingfile=mappingfile;
		try{
			parser = factory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			xmlReader.setContentHandler(helperHandler);
	
			InputStream stream = new FileInputStream(this.mappingfile);
			InputSource is = new InputSource(stream);
			xmlReader.parse(is);
		}catch(Exception e){
			e.printStackTrace();
		}
		ObjectTreeData=helperHandler.getItems();
	}
	
	public WebElement fetchWebElement(String name, WebDriver driver){
		if(driver==null || ObjectTreeData==null || name==null)return null;
		for(ObjectItem item : ObjectTreeData){
			if(name.equalsIgnoreCase(item.getName())){
				String value=item.getValue();
				String property=item.getProperty();
				By by=null;
				if(property!=null && !property.equals("")){
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_ID)){
						by=By.id(value);
					}
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_CSS)){
						by=By.cssSelector(value);
					}
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_LINK)){
						by=By.linkText(value);
					}
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_NAME)){
						by=By.name(value);
					}
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_XPATH)){
						by=By.xpath(value);
					}
					if(property.toLowerCase().equalsIgnoreCase(ILocator.LOC_CLASS)){
						by=By.className(value);
					}
				}
				if(by!=null){
					return driver.findElement(by);
				}
			}
		}
		
		return null;
	}
}
