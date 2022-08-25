package ${cfg.packagePrefix}.web.boss.controller.${cfg.packageName!};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${cfg.packagePrefix}.common.core.base.BaseController;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.qo.${entity}QO;
import ${cfg.packagePrefix}.web.boss.biz.${cfg.packageName!}.${entity}Biz;

/**
* ${table.comment}
*
* @author ${author}
* @date ${date}
*/
@Controller
@RequestMapping(value = "/${cfg.packageName!}/${table.entityPath}")
public class ${entity}Controller extends BaseController {

private final static String TARGETID = "${cfg.packageName!}-${table.entityPath}";

@Autowired
private ${entity}Biz biz;

@GetMapping(value = "/list")
public void list(@ModelAttribute ${entity}QO qo, ModelMap modelMap){
modelMap.put("page", biz.listForPage(qo));
modelMap.put("bean", qo);
}

@GetMapping(value = "/add")
public void add(){

}

@ResponseBody
@PostMapping(value = "/save")
public String save(@ModelAttribute ${entity}QO qo){
if (biz.save(qo) > 0) {
return success(TARGETID);
}
return error("添加失败");
}

@ResponseBody
@PostMapping(value = "/delete")
public String delete(@RequestParam(value = "id") Long id){
if (biz.deleteById(id) > 0) {
return delete(TARGETID);
}
return error("删除失败");
}

@GetMapping(value = "/edit")
public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap){
modelMap.put("bean", biz.getById(id));
}

@ResponseBody
@PostMapping(value = "/update")
public String update(@ModelAttribute ${entity}QO qo){
if (biz.updateById(qo) > 0) {
return success(TARGETID);
}
return error("修改失败");
}

@GetMapping(value = "/view")
public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
modelMap.put("bean", biz.getById(id));
}

}
