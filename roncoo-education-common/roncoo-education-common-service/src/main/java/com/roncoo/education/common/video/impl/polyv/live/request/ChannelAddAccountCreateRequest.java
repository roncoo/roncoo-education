package com.roncoo.education.common.video.impl.polyv.live.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 创建频道的助教或嘉宾角色
 */
@Data
public class ChannelAddAccountCreateRequest implements Serializable {

    private static final long serialVersionUID = 8358132334891912486L;

    /**
     * 角色 Assistant：助教 Guest：嘉宾
     */
    private String role;

    /**
     * 头衔，长度1~8位，助教默认为助教
     */
    private String actor;

    /**
     * 昵称，长度1~8位，默认随机生成昵称
     */
    private String nickName;

    /**
     * 头像，默认初始头像（JPG、PNG格式图片）
     */
    private String avatar;

    /**
     * 角色密码，长度6~16位，默认随机生成密码
     */
    private String passwd;

    /**
     * 权限
     */
    private List<Purview> purviewList;


    @Data
    @Accessors(chain = true)
    public static class Purview implements Serializable {
        private static final long serialVersionUID = 4958817773491465169L;

        /**
         * 权限(chatListEnabled：在线列表（仅支持助教）、
         * pageTurnEnabled：翻页（仅支持助教，且仅能设置一个助教有翻页权限）
         * monitorEnabled：监播（仅支持助教，且仅能设置一个助教有监播权限）
         * chatAuditEnabled：聊天审核（仅支持助教）)
         */
        private String code;

        /**
         * 开关(Y：开启 N：关闭)
         */
        private String enabled;
    }
}
