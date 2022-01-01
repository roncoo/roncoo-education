package com.roncoo.education.course.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.pc.biz.PcApiZoneBiz;
import com.roncoo.education.course.service.pc.req.*;
import com.roncoo.education.course.service.pc.resq.ZonePageRESQ;
import com.roncoo.education.course.service.pc.resq.ZoneViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专区表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/course/pc/zone")
public class PcApiZoneController extends BaseController {

    @Autowired
    private PcApiZoneBiz biz;

    /**
     * 分页列出专区信息
     *
     * @param zonePageREQ
     * @return
     */
    @ApiOperation(value = "分页列出专区信息", notes = "分页列出专区信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<ZonePageRESQ>> listForPage(@RequestBody ZonePageREQ zonePageREQ) {
        return biz.listForPage(zonePageREQ);
    }

    /**
     * 添加专区信息
     *
     * @param zoneSaveREQ
     * @return
     */
    @ApiOperation(value = "添加专区信息", notes = "添加专区信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody ZoneSaveREQ zoneSaveREQ) {
        return biz.save(zoneSaveREQ);
    }

    /**
     * 删除专区信息
     *
     * @param zoneDeleteREQ
     * @return
     */
    @ApiOperation(value = "删除专区信息", notes = "删除专区信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody ZoneDeleteREQ zoneDeleteREQ) {
        return biz.delete(zoneDeleteREQ);
    }

    /**
     * 更新专区信息
     *
     * @param zoneUpdateREQ
     * @return
     */
    @ApiOperation(value = "更新专区信息", notes = "更新专区信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody ZoneUpdateREQ zoneUpdateREQ) {
        return biz.update(zoneUpdateREQ);
    }

    /**
     * 查看专区信息
     *
     * @param zoneViewREQ
     * @return
     */
    @ApiOperation(value = "查看专区信息", notes = "查看专区信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<ZoneViewRESQ> view(@RequestBody ZoneViewREQ zoneViewREQ) {
        return biz.view(zoneViewREQ);
    }

}
