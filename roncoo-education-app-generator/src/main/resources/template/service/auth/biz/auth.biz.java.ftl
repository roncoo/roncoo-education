package ${cfg.packagePrefix}.${cfg.packageName!}.service.auth.biz;

import ${cfg.packagePrefix}.common.service.BaseBiz;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.${entity}Dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-${table.comment!}
 *
 * @author ${author}
 */
@Component
@RequiredArgsConstructor
public class Auth${entity}Biz extends BaseBiz {

    @NotNull
    private final ${entity}Dao dao;

}
