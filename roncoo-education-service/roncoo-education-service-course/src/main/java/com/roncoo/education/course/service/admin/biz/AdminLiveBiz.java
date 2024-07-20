package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.util.StrUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LiveDelayEnum;
import com.roncoo.education.common.core.enums.LiveModelEnum;
import com.roncoo.education.common.core.enums.LiveSceneEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.LiveUtil;
import com.roncoo.education.common.video.req.LiveBroadcastReq;
import com.roncoo.education.common.video.req.LiveChannelReq;
import com.roncoo.education.common.video.resp.LiveChannelResp;
import com.roncoo.education.course.dao.LiveDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminLiveBroadcastReq;
import com.roncoo.education.course.service.admin.req.AdminLiveEditReq;
import com.roncoo.education.course.service.admin.req.AdminLivePageReq;
import com.roncoo.education.course.service.admin.req.AdminLiveSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminLivePageResp;
import com.roncoo.education.course.service.admin.resp.AdminLiveViewResp;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-直播信息
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class AdminLiveBiz extends BaseBiz {

    @NotNull
    private final LiveDao dao;

    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final IFeignLecturer feignLecturer;

    /**
     * 获取开播地址
     *
     * @param req 参数
     * @return 开播地址
     */
    public Result<String> broadcast(AdminLiveBroadcastReq req) {
        Live live = dao.getById(req.getId());
        VideoConfig videoConfig = feignSysConfig.getVideo();

        Live channel = new Live();
        channel.setId(live.getId());
        channel.setLivePlatform(videoConfig.getLivePlatform());
        if (StrUtil.isBlank(live.getChannelId())) {
            // 创建频道
            LiveChannelReq channelReq = new LiveChannelReq();
            channelReq.setLiveName(live.getLiveName());
            channelReq.setLiveScene(LiveSceneEnum.LARGE_CLASS);
            channelReq.setLiveModel(LiveModelEnum.byCode(live.getLiveModel()));
            LiveChannelResp channelResp = LiveUtil.getLiveChannel(videoConfig, channelReq);
            if (channelResp == null) {
                return Result.error("获取直播频道失败");
            }
            channel.setChannelId(channelResp.getChannelId());
            channel.setChannelPwd(channelResp.getChannelPwd());
            dao.updateById(channel);
        }

        // 获取开播地址
        LiveBroadcastReq broadcastReq = new LiveBroadcastReq();
        broadcastReq.setEnableMarquee(videoConfig.getLiveEnableMarquee());
        broadcastReq.setWebrtc(live.getLiveDelay().equals(LiveDelayEnum.WEBRTC.getCode()));
        broadcastReq.setChannelId(channel.getChannelId());
        broadcastReq.setChannelPwd(channel.getChannelPwd());
        broadcastReq.setLiveName(live.getLiveName());
        broadcastReq.setLiveDesc(live.getLiveIntroduce());
        // 讲师信息
        LecturerViewVO lecturerViewVO = feignLecturer.getById(live.getLecturerId());
        broadcastReq.setUserId(lecturerViewVO.getId());
        broadcastReq.setUserName(lecturerViewVO.getLecturerName());
        broadcastReq.setUserAvatar(lecturerViewVO.getLecturerHead());
        return Result.success(LiveUtil.getLiveBroadcastUrl(videoConfig, broadcastReq));
    }

    /**
     * 直播信息分页
     *
     * @param req 直播信息分页查询参数
     * @return 直播信息分页查询结果
     */
    public Result<Page<AdminLivePageResp>> page(AdminLivePageReq req) {
        LiveExample example = new LiveExample();
        Criteria c = example.createCriteria();
        Page<Live> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLivePageResp> respPage = PageUtil.transform(page, AdminLivePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 直播信息添加
     *
     * @param req 直播信息
     * @return 添加结果
     */
    public Result<String> save(AdminLiveSaveReq req) {
        Live record = BeanUtil.copyProperties(req, Live.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 直播信息查看
     *
     * @param id 主键ID
     * @return 直播信息
     */
    public Result<AdminLiveViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLiveViewResp.class));
    }

    /**
     * 直播信息修改
     *
     * @param req 直播信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLiveEditReq req) {
        Live record = BeanUtil.copyProperties(req, Live.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 直播信息删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
