package org.gennerator.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateUtil {

	private static TemplateUtil template;

	public static TemplateUtil getInstance() {
		if (template == null) {
			template = new TemplateUtil();
		}
		return template;
	}

	/**
	 * 模板的存放位置
	 */
	private static final String TEMPLATE_PATH = "/tpl";
	/**
	 * 启动模板缓存
	 */
	private static final Map<String, Template> TEMPLATE_CACHE = new HashMap<String, Template>();
	/**
	 * 模板文件后缀
	 */
	private static final String SUFFIX = ".ftl";
	/**
	 * 模板引擎配置
	 */
	private Configuration configuration;

	public String getText(String templateId, Map<String, Object> parameters) {
		String templateFile = templateId + SUFFIX;
		try {
			Template template = TEMPLATE_CACHE.get(templateFile);
			if (template == null) {
				template = this.getConfiguration().getTemplate(templateFile);
				TEMPLATE_CACHE.put(templateFile, template);
			}
			StringWriter stringWriter = new StringWriter();
			template.process(parameters, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration();

			configuration.setClassForTemplateLoading(TemplateUtil.class,
					TEMPLATE_PATH);
			configuration.setEncoding(Locale.getDefault(), "UTF-8");
			configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return configuration;
	}
}
