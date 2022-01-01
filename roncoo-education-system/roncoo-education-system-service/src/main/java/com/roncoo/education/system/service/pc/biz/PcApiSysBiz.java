package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.SysTypeEnum;
import com.roncoo.education.common.core.enums.VideoTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Sys;
import com.roncoo.education.system.service.pc.req.SysUpdateRESQ;
import com.roncoo.education.system.service.pc.resq.SysViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置表
 */
@Component
public class PcApiSysBiz {

    @Autowired
    private SysDao dao;

    public Result<SysViewRESQ> view() {
        Sys sys = dao.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("找不到系统配置信息");
        }
        SysViewRESQ resq = BeanUtil.copyProperties(sys, SysViewRESQ.class);
        resq.setPolyvUseid("******");
        resq.setPolyvWritetoken("******");
        resq.setPolyvReadtoken("******");
        resq.setPolyvSecretkey("******");
        resq.setAliyunAccessKeyId("******");
        resq.setAliyunAccessKeySecret("******");
        resq.setAliyunOssUrl("******");
        resq.setAliyunOssBucket("******");
        resq.setSmsCode("******");
        resq.setSignName("******");
        resq.setPayKey("******");
        resq.setPaySecret("******");
        resq.setPayUrl("******");
        resq.setNotifyUrl("******");
        return Result.success(resq);
    }

    public Result<Integer> update(SysUpdateRESQ resq) {
        if (resq.getId() == null) {
            return Result.error("id不能为空");
        }
        Sys record = BeanUtil.copyProperties(resq, Sys.class);
        if (VideoTypeEnum.OTHER.getCode().equals(record.getVideoType())) {
            throw new BaseException("视频存储暂未支持其他视频云");
        }
        if (SysTypeEnum.OTHERPAYMENT.getCode().equals(record.getPayType())) {
            throw new BaseException("支付通道暂时只支持龙果支付");
        }
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

}
