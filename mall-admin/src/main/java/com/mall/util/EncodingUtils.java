package com.mall.util;

/**
 * 编码检测工具集
 * @author ChenMingcai
 * 2016-02-03
 *
 */

public class EncodingUtils
{
	/**
	 * 判断字符串中是否包含乱码
	 * 用getBytes(encoding)：返回字符串的一个byte数组  
     *  当b[0]为  63时，应该是转码错误  
     *  A、不乱码的汉字字符串：  
     *  1、encoding用GB2312时，每byte是负数；  
     *  2、encoding用ISO8859_1时，b[i]全是63。  
     *  B、乱码的汉字字符串：  
     *  1、encoding用ISO8859_1时，每byte也是负数；  
     *  2、encoding用GB2312时，b[i]大部分是63。  
     *  C、英文字符串  
     *  1、encoding用ISO8859_1和GB2312时，每byte都大于0；  
     *  <p/>  
     *  总结：给定一个字符串，用getBytes("iso8859_1")  
     *  1、如果b[i]有63，不用转码；  A-2  
     *  2、如果b[i]全大于0，那么为英文字符串，不用转码；  B-1  
     *  3、如果b[i]有小于0的，那么已经乱码，要转码。  C-1 
	 * @param text
	 * @return
	 */
	public static boolean errorEncoding(String text)
	{
		try
		{
			byte[] bytes = text.getBytes("ISO-8859-1");
			for(int i = 0; i < bytes.length; i++)
			{
				byte b0 = bytes[i];
				if(b0 == 63)
				{
					break;
				}
				else if(b0 > 0)
				{
					continue;
				}
				else if(b0 < 0)
				{
					return true;
				}
			}
		}
		catch(Exception ex)
		{
			
		}
		return false;
	}
}
