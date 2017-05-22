package com.mall.util;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mall.bean.Attachment;
import com.mall.bean.SystemConfig;

public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 检测表达式前缀
	 */
	private static final String EXP_PREFIX = ".*\\.(";

	/**
	 * 检测表达式后缀
	 */
	private static final String EXP_SUFFIX = ")$";

	/**
	 * 默认图片扩展名检测表达式: jpg, jpeg, png, gif, bmp
	 */
	private static final String IMAGE_DEFAULT_EXP = EXP_PREFIX + "(jpg)|(jpeg)|(png)|(gif)|(bmp)" + EXP_SUFFIX;

	/**
	 * 默认文件扩展名检测表达式：zip, rar
	 */
	private static final String FILE_DEFAULT_EXP = EXP_PREFIX + "(zip)|(rar)" + EXP_SUFFIX;

	/**
	 * 检测指定上传类型的文件名是否合法
	 * 
	 * @param type
	 *            文件类型
	 * @param name
	 *            文件名称
	 * @return 检测结果
	 */
	public static boolean checkFilename(String type, String name) {
		if (type == null || name == null) {
			return false;
		}

		SystemConfig config = SystemConfigUtil.getSystemConfig();

		String checkExp = IMAGE_DEFAULT_EXP;
		String[] extNames = null;
		if ("image".equalsIgnoreCase(type)) {
			String extString = config.getUploadImageExtension();
			if (extString != null && !"".equals(extString.trim())) {
				extNames = extString.split(",");
			} else {
				logger.warn("系统设置中允许上传的图片扩展名未设置 , 系统使用默认值:{}", IMAGE_DEFAULT_EXP);
				checkExp = IMAGE_DEFAULT_EXP;
			}
		} else if ("file".equalsIgnoreCase(type)) {
			String extString = config.getUploadFileExtension();
			if (extString != null && !"".equals(extString.trim())) {
				extNames = extString.split(",");
			} else {
				logger.warn("系统设置中允许上传的文件扩展名未设置 , 系统使用默认值:{}", FILE_DEFAULT_EXP);
				checkExp = FILE_DEFAULT_EXP;
			}
		}

		if (extNames != null && extNames.length > 0) {
			StringBuffer builder = new StringBuffer(EXP_PREFIX);
			for (String ext : extNames) {
				builder.append("(").append(ext).append(")|");
			}
			builder.deleteCharAt(builder.length() - 1).append(EXP_SUFFIX);
			checkExp = builder.toString();
			logger.info("此次文件上传检测表达式为:{}", checkExp);
		}

		return name.matches(checkExp);
	}

	/**
	 * 检测文件大小是否超过系统设置
	 * 
	 * @param size
	 *            检测大小
	 * @return 检测结果
	 */
	public static boolean checkFileSize(long size) {
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		if (config.getUploadMaxSize() > 0) {
			long limit = config.getUploadMaxSize() * 1024 * 1024;
			return size <= limit;
		}

		return true;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 *            文件名称
	 * @return 返回文件扩展名
	 */
	public static String getExtension(String filename) {
		String name = "";

		if (filename != null && !"".equals(filename)) {
			int idx = filename.lastIndexOf('.');
			if (idx > 0) {
				name = filename.substring(idx + 1);
			}
		}

		return name;
	}

	/**
	 * 获取路径path下的文件列表
	 * 
	 * @param path
	 * @return
	 */
	public static List<Attachment> listFiles(String root, String path) {
		List<Attachment> list = new LinkedList<Attachment>();
		File file = new File(root + path);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File fl : files) {
				Attachment model = new Attachment();
				model.setName(fl.getName());
				model.setSize(fl.length() / 1024);
				model.setLastModified(new Date(fl.lastModified()));
				model.setDirectory(fl.isDirectory());
				model.setUrl(path + fl.getName());
				list.add(model);
			}
		}
		return list;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void deleteFile(String root, String file) {
		File delFile = new File(root + file);
		if (delFile.exists()) {
			delFile.delete();
		}
	}
}
