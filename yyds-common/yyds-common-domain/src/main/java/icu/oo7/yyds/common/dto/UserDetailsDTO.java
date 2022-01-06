package icu.oo7.yyds.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 平台用户信息传输对象
 *
 * @author peng.zhao
 */
@Data
public class UserDetailsDTO implements Serializable {

    private static final long serialVersionUID = -286971866427130259L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端可访问资源集合
     */
    private List<String> resourceIds;

}
