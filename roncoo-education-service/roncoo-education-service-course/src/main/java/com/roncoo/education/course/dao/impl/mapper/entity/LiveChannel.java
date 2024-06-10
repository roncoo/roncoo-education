package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LiveChannel implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private Long courseId;

    private Integer livePlatform;

    private String channelNo;

    private String channelPwd;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getLivePlatform() {
        return livePlatform;
    }

    public void setLivePlatform(Integer livePlatform) {
        this.livePlatform = livePlatform;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelPwd() {
        return channelPwd;
    }

    public void setChannelPwd(String channelPwd) {
        this.channelPwd = channelPwd == null ? null : channelPwd.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", courseId=").append(courseId);
        sb.append(", livePlatform=").append(livePlatform);
        sb.append(", channelNo=").append(channelNo);
        sb.append(", channelPwd=").append(channelPwd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}