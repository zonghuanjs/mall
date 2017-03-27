package cn.tekism.mall.util;

import java.io.StringReader;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * xml文件验证器
 * @author zonghuan
 *
 */

public class XmlValidatorUtils
{
	private static final String XML_SCHEME_INSTANCE = "http://www.w3.org/2001/XMLSchema";
	
	/**
	 * 使用xsd文件验证xml内容
	 * @param xsdName
	 * @param xmlContent
	 * @return
	 */
	public static void validateXml(String xsdFile, String xmlContent) throws XmlValidateException
	{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XML_SCHEME_INSTANCE);
		URL schemeUrl = Thread.currentThread().getContextClassLoader().getResource(xsdFile);
		if(schemeUrl == null)
		{
			throw new XmlValidateException("xml规则文件不存在：" + xsdFile);
		}
		
		try
		{
			Schema schema = schemaFactory.newSchema(schemeUrl);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(new StringReader(xmlContent));
			validator.validate(source);
		}
		catch(Exception ex)
		{
			throw new XmlValidateException(ex.getMessage());
		}
	}
	
	public static class XmlValidateException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public XmlValidateException(String message)
		{
			super(message);
		}
	}
}
