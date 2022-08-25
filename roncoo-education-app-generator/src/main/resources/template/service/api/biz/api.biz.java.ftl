package ${cfg.packagePrefix}.${cfg.packageName!}.service.api.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${cfg.packagePrefix}.common.core.base.BaseBiz;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.${entity}Dao;

/**
* ${table.comment!}
*
* @author ${author}
*/
@Component
public class Api${entity}Biz extends BaseBiz {

@Autowired
private ${entity}Dao dao;

}
