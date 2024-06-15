package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Live implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long lecturerId;

    private String liveName;

    private Integer liveModel;

    private Integer liveDelay;

    private LocalDateTime beginTime;

    private Integer liveDuration;

    private Integer liveStatus;

    private Integer livePlatform;

    private String channelId;

    private String channelPwd;

    private Integer playbackSave;

    private Long resourceId;

    private String liveIntroduce;

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

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName == null ? null : liveName.trim();
    }

    public Integer getLiveModel() {
        return liveModel;
    }

    public void setLiveModel(Integer liveModel) {
        this.liveModel = liveModel;
    }

    public Integer getLiveDelay() {
        return liveDelay;
    }

    public void setLiveDelay(Integer liveDelay) {
        this.liveDelay = liveDelay;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(Integer liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public Integer getLivePlatform() {
        return livePlatform;
    }

    public void setLivePlatform(Integer livePlatform) {
        this.livePlatform = livePlatform;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelPwd() {
        return channelPwd;
    }

    public void setChannelPwd(String channelPwd) {
        this.channelPwd = channelPwd == null ? null : channelPwd.trim();
    }

    public Integer getPlaybackSave() {
        return playbackSave;
    }

    public void setPlaybackSave(Integer playbackSave) {
        this.playbackSave = playbackSave;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getLiveIntroduce() {
        return liveIntroduce;
    }

    public void setLiveIntroduce(String liveIntroduce) {
        this.liveIntroduce = liveIntroduce == null ? null : liveIntroduce.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", statusId=").append(statusId);
        sb.append(", sort=").append(sort);
        sb.append(", lecturerId=").append(lecturerId);
        sb.append(", liveName=").append(liveName);
        sb.append(", liveModel=").append(liveModel);
        sb.append(", liveDelay=").append(liveDelay);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", liveDuration=").append(liveDuration);
        sb.append(", liveStatus=").append(liveStatus);
        sb.append(", livePlatform=").append(livePlatform);
        sb.append(", channelId=").append(channelId);
        sb.append(", channelPwd=").append(channelPwd);
        sb.append(", playbackSave=").append(playbackSave);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", liveIntroduce=").append(liveIntroduce);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}