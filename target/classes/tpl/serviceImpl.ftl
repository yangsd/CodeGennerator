package ${package};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${daoclass};
import ${voclass};
import ${serviceclass};
/**
 * ${table.aliasName}基础服务接口实现
 * 
 * @author sdyang
 * @date ${date}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    ${table.daoName} dao;

    @Override
    public List<${table.beanName}> getListByPage(String order, String sort,
	    int currentpage, int pagesize) {
	return dao.getListByPage(order, sort, currentpage, pagesize);
    }

    @Override
    public int getCount() {
		return dao.getCount();
    }

    @Override
    public void add${table.aliasName}(${table.beanName} vo){
    	dao.add${table.aliasName}(vo);
    }
    
    @Override
    public void update${table.aliasName}(${table.beanName} vo){
    	dao.update${table.aliasName}(vo);
    }
	
	@Override
    public void delete${table.aliasName}(Integer ${table.primary_key}){
    	dao.delete${table.aliasName}(${table.primary_key});
    }
    
    @Override
    public ${table.beanName} get${table.aliasName}ById(int ${table.primary_key}){
		return dao.get${table.aliasName}ById(${table.primary_key});
    }

}
