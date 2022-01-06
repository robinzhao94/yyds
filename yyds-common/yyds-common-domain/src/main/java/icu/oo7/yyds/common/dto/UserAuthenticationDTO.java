package icu.oo7.yyds.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户认证信息传输类
 * @author peng.zhao
 */
@Data
public class UserAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 541408156115438242L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户客户端ID
     */
    private String clientId;
}
