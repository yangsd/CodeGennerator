package ${package};

import java.util.Date;

/**
 * ${table.beanName}类
 * ${table.comment}
 * 
 * @author sdyang
 * @date ${date}
 */
public class ${table.beanName} {

	<#list fields as field> 
		/**
		 * ${field.comment}
		 */
		private ${field.type} ${field.column};
		
	</#list> 
		
	<#list fields as field> 
		/**
		 * 获取${field.column}
		 * ${field.comment}
		 */
		public ${field.type} get${field.property}(){
			return ${field.column};
		}
		
		/**
		 * 设置${field.column}
		 * ${field.comment}
		 */
		public void set${field.property}(${field.type} ${field.column}) {
			this.${field.column} = ${field.column};
		}
		
	</#list>
}
