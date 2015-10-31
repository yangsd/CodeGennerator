package org.gennerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtil {

	/**
	 * 获取模板文件
	 * 
	 * @param filePath
	 * @return
	 * @author sdyang
	 * @date 2015年9月28日 下午2:44:40
	 */
	public static Resource getTemplateFile(String filePath) {
		Resource r = new ClassPathResource(filePath);
		return r;
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 * @param content
	 * @throws IOException
	 * @author sdyang
	 * @date 2015年10月29日 下午6:18:43
	 */
	public static void createFile(String path, String content)
			throws IOException {

		File file = new File(path);
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		BufferedWriter buffw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(buffw);
		pw.println(content);

		pw.close();
		buffw.close();
		fw.close();
	}

}
