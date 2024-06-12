package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LiveLog implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private Long liveId;

    private Integer livePlatform;

    private Integer liveStatus;

    private String channelId;

    private String sessionId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer playbackSave;

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

    public Long getLiveId() {
        return liveId;
    }

    public void setLiveId(Long liveId) {
        this.liveId = liveId;
    }

    public Integer getLivePlatform() {
        return livePlatform;
    }

    public void setLivePlatform(Integer livePlatform) {
        this.livePlatform = livePlatform;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPlaybackSave() {
        return playbackSave;
    }

    public void setPlaybackSave(Integer playbackSave) {
        this.playbackSave = playbackSave;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", liveId=").append(liveId);
        sb.append(", livePlatform=").append(livePlatform);
        sb.append(", liveStatus=").append(liveStatus);
        sb.append(", channelId=").append(channelId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", playbackSave=").append(playbackSave);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}