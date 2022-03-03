package icu.oo7.yyds.uaa.feign.fallback;

import icu.oo7.yyds.common.dto.UserDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseCode;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.RbacFeignService;
import org.springframework.stereotype.Service;

/**
 * RBAC 服务消费者降级回调实现
 *
 * @author peng.zhao
 */
public class RbacFeignServiceImpl implements RbacFeignService {

    @Override
    public ResponseData<UserDetailsDTO> getUserByUsername(String username) {
        return ResponseData.failure(ResponseCode.RC206.getCode(), ResponseCode.RC206.getMessage());
    }
}
