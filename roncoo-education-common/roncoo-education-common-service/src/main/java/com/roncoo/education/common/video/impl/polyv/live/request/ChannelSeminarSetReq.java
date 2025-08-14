package com.roncoo.education.common.video.impl.polyv.live.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 研讨会参会条件
 *
 * @author fengyw
 */
@Data
public class ChannelSeminarSetReq implements Serializable {

    private static final long serialVersionUID = 4958817773491465169L;

    private List<AuthSetting> authSettings;

    @Data
    public static class AuthSetting {
        // 用于实现一个频道设置两个参会条件，为1或2（1为主要条件，2为次要条件）
        private Integer rank;

        // 是否开启参会条件（Y：开启，N：不开启）
        private String enabled;

        // 角色（host：主持人，attendee：参会人）
        private String roleCode;

        // 参会条件类型（password：密码登录，direct：独立授权，custom：自定义授权）
        private String authType;

        // 当authType为password时，密码登录的password
        private String password;

        // 当authType为direct时，设置参数，非必填，独立授权SecretKey
        private String directKey;

        // 当authType为custom时，为自定义授权自定义URL
        private String customUri;

        // 当authType为custom时，为自定义授权SecretKey
        private String customKey;
    }
}
