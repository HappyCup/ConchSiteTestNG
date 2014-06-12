package test_utils;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPraserHelper extends DefaultHandler {

	private String Tag = "SAXPraserHelper";
	private List<ObjectItem> items;
	private ObjectItem item;

	private String currentTag;

	public List<ObjectItem> getItems() {
		return items;
	}

	// 当解析到文档开始时的回调方法
	@Override
	public void startDocument() throws SAXException {
		items = new ArrayList<ObjectItem>();
	}

	// 当解析到xml的标签时的回调方法
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		item = new ObjectItem();
		item.setName(qName);
		item.setProperty(attributes.getValue("propertyName"));
		item.setValue(attributes.getValue("value"));
		// 设置当前的标签名
		currentTag = qName;
	}

	// 当解析到xml的结束标签时的回调方法
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (currentTag.equals(qName) && item!=null) {
			items.add(item);
			item = null;
		}
	}
}
