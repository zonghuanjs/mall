package cn.tekism.mall.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片处理工具类
 * @author zonghuan
 *
 */

public class ImageUtils
{
	/**
	 * 图片等比例缩放为指定大小,空白部分以白色填充
	 * @param srcImage		源图片
	 * @param destFile		缩放后图片
	 * @param destWidth		目标宽度
	 * @param destHeight	目标高度
	 */
	public static void zoom(BufferedImage srcImage, File destFile, int destWidth, int destHeight)
	{
		int imgWidth = destWidth;
		int imgHeight = destHeight;
		
		try
		{
			int srcWidth = srcImage.getWidth();
			int srcHeight = srcImage.getHeight();
			
			if(srcHeight >= srcWidth)
			{
				imgWidth = (int)Math.round((destHeight * 1.0 / srcHeight) * srcWidth);
			}
			else
			{
				imgHeight = (int)Math.round((destWidth * 1.0 / srcWidth) * srcHeight);
			}
			
			BufferedImage destImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = destImage.createGraphics();
			graphics.setBackground(Color.WHITE);
			graphics.clearRect(0, 0, destWidth, destHeight);
			int posX = (destWidth - imgWidth) / 2;
			int posY = (destHeight - imgHeight) / 2;
			graphics.drawImage(srcImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), posX, posY, null);
			graphics.dispose();
			ImageIO.write(destImage, "JPEG", destFile);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 判断文件是否是图片
	 * @param file		文件对象
	 * @return	如果是, 返回true; 否则, 返回false
	 */
	public static boolean isImage(File file)
	{
		boolean is = false;
		
		if(file == null)
		{
			return false;
		}
		
		Image img = null;
		try
		{
			img = ImageIO.read(file);
			if(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0)
			{
				return false;
			}
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return is;
	}
	
	/**
	 * 判断文件是否是图片
	 * @param file		文件对象
	 * @return	如果是, 返回true; 否则, 返回false
	 */
	public static boolean isImage(InputStream ins)
	{
		boolean is = false;
		
		if(ins == null)
		{
			return false;
		}
		
		Image img = null;
		try
		{
			img = ImageIO.read(ins);
			if(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0)
			{
				return false;
			}
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return is;
	}
}
