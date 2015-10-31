package ${package};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import ${voclass};
import ${serviceclass};
//import com.kuyu.vo.DataGridVO;

/**
 * ${table.controllerName}
 * 
 * @author sdyang
 * @date ${date}
 */
@Controller
public class ${table.controllerName} {

    @Autowired
    ${table.serviceName} service;

    @RequestMapping("/${table.name}Page")
    public ModelAndView get${table.aliasName}Page() {
		ModelAndView mav = new ModelAndView("${table.name}/${table.name}Page");
		return mav;
    }

    @RequestMapping("/list${table.aliasName}")
    @ResponseBody
    public String list${table.aliasName}(String order, String sort, String page, String rows) {
		Gson gson = new Gson();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
			: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
			: rows);// 每页多少行
		currentpage = (currentpage - 1) * pagesize;
		DataGridVO vo = new DataGridVO();
		List<${table.beanName}> ${table.name}List = service.getListByPage(order, sort, currentpage,
			pagesize);
		vo.setRows(${table.name}List);
		vo.setTotal(service.getCount() + "");
		return gson.toJson(vo);
    }
 
	@RequestMapping("/${table.name}OperatePage")
    public ModelAndView getOperatePage(String oper, Integer ${table.primary_key}) {
		ModelAndView mav = new ModelAndView("${table.name}/${table.name}OperatePage");
		if (${table.primary_key} == null) {
		    ${table.primary_key} = -1;
		}
		${table.beanName} vo = service.get${table.aliasName}ById(${table.primary_key});
		mav.addObject("operate", oper);
		if ("modify".equals(oper)) {
		    mav.addObject("vo", vo);
		}
		return mav;
    }

    @RequestMapping("/save${table.aliasName}")
    public ModelAndView save${table.aliasName}(${table.beanName} vo, BindingResult result,
	    String operate) {

		if (result.hasErrors()) {
		    ModelAndView mavErr = new ModelAndView("${table.name}/${table.name}OperatePage");
		    mavErr.addObject("operate", operate);
		    mavErr.addObject("vo", vo);
		    mavErr.addObject("fieldErrors", result.getFieldErrors());
		    return mavErr;
		}
	
		if ("add".equals(operate)) {
		    service.add${table.aliasName}(vo);
		} else {
		    service.update${table.aliasName}(vo);
		}
		ModelAndView mav = new ModelAndView("${table.name}/${table.name}Page");
		mav.addObject("saveResult", "SUCCESS");
		return mav;
    }

    @RequestMapping("/delete${table.aliasName}")
    @ResponseBody
    public String delete${table.aliasName}(int ${table.primary_key}) {
		try {
		    service.delete${table.aliasName}(${table.primary_key});
		} catch (Exception e) {
		    return e.getMessage();
		}
		return "SUCCESS";
    }
}
