package com.roncoo.education.system.service.api.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.req.SysUpdateRESQ;
import com.roncoo.education.system.common.resq.SysViewRESQ;
import com.roncoo.education.system.service.dao.SysDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Sys;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.SysTypeEnum;
import com.roncoo.education.util.enums.VideoTypeEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

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
		if (VideoTypeEnum.QINIU.getCode().equals(record.getVideoType())) {
			throw new BaseException("视频存储暂未支持七牛");
		}
		if (FileTypeEnum.QINIU.getCode().equals(record.getFileType())) {
			throw new BaseException("文件存储暂未支持七牛");
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
