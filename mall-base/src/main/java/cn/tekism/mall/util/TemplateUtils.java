package cn.tekism.mall.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public final class TemplateUtils
{
	private static String TemplatesRoot;
	private static final String path = "templates";//模板根目录
	
	static
	{
		try
		{
			TemplatesRoot = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()).getParent() + File.separator + path;
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取模板内容
	 * @param template 模板名称(以文件分隔符开始)
	 * @return 模板文件内容
	 */
	public static String getTemplate(String template)
	{
		StringBuffer buffer = new StringBuffer();
		File file = new File(TemplatesRoot + template);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file)), "utf-8"));
			String line;
			while((line = reader.readLine()) != null)
			{
				buffer.append(line + "\r\n");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch(Exception ex)
				{
					
				}
			}
		}
		return buffer.toString();
	}
}
