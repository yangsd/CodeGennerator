package ${package};

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ${voclass};
/**
 * ${table.aliasName}数据访问
 * 
 * @author sdyang
 * @date ${date}
 */
public interface ${table.daoName} {
    
    public List<${table.beanName}> getListByPage(@Param(value = "order") String order,
	    @Param(value = "sort") String sort,
	    @Param(value = "currentpage") int currentpage,
	    @Param(value = "pagesize") int pagesize);

	public int getCount();
	
    public void add${table.aliasName}(${table.beanName} vo);

    public void update${table.aliasName}(${table.beanName} vo);

    public void delete${table.aliasName}(Integer ${table.primary_key});

    public ${table.beanName} get${table.aliasName}ById(int ${table.primary_key});
}
