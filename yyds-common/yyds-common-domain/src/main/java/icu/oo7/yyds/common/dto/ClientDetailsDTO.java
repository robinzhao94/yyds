package icu.oo7.yyds.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Oauth2客户端传输对象
 *
 * @author peng.zhao
 */
@Data
public class ClientDetailsDTO implements Serializable {

    private static final long serialVersionUID = -5602937381699674476L;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端可访问资源集合
     */
    private String resourceIds;

    /**
     * 客户端范围
     */
    private String scope;

    /**
     * 客户端授权类型
     */
    private String authorizedGrantTypes;

    /**
     * 授予OAuth客户端的权限
     * 无法返回null
     * 请注意，这些不是授予具有授权访问令牌的用户的权限。
     * 相反，这些权限是客户本身固有的
     */
    private String authorities;

    /**
     * 此客户端在”authorization_code”访问授权期间使用的预定义重定向URI
     */
    private String webServerRedirectUri;
}
