package cn.tekism.mall.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Assert;

/**
 * Json工具类
 * @author ChenMingcai
 *
 */

public final class JsonUtil
{
	/**
	 * 将对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String fromObject(Object obj)
	{
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(writer);
			generator.writeObject(obj);
			generator.flush();
		}
		catch(IOException ex)
		{
			
		}
		return writer.toString();
	}
	
	/**
	 * 将json字符串转换成对象
	 * @param json
	 * @return
	 */
	public static<T> T objectFromJson(String json, Class<T> clazz)
	{
		ObjectMapper mapper = new ObjectMapper();
		T object = null;
		try
		{
			object = mapper.readValue(json, clazz);
		}
		catch(Exception ex)
		{
			
		}
		return object;
	}
	
	/**
	 * 输出json字符串
	 * @param response  响应对象
	 * @param jsonStr   json字符串
	 */
	public static void writeJson(HttpServletResponse response, String jsonStr)
	{
		Assert.notNull(response);
		Assert.hasText(jsonStr);
		try
		{
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().print(jsonStr);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
