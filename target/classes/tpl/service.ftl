package ${package};

import java.util.List;

import ${voclass};

/**
 * ${table.aliasName}基础服务接口
 * 
 * @author sdyang
 * @date ${date}
 */
public interface ${table.serviceName} {
	
	/**
	 * 分页查询
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public List<${table.beanName}> getListByPage(String order,String sort,int currentpage,int pagesize);

	/**
	 * 获取总记录数
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public int getCount();
    
	/**
	 * 新增操作
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public void add${table.aliasName}(${table.beanName} vo);

	/**
	 * 更新操作
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public void update${table.aliasName}(${table.beanName} vo);

	/**
	 * 删除操作
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public void delete${table.aliasName}(Integer ${table.primary_key});	
    
   /**
	 * 根据主键查询VO
	 * 
	 * @author sdyang
	 * @date ${date}
	 */
    public ${table.beanName} get${table.aliasName}ById(int ${table.primary_key});
}