package icu.oo7.yyds.uaa.feign;

import icu.oo7.yyds.common.dto.UserDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.fallback.RbacFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "yyds-admin-rbac", fallback = RbacFeignServiceImpl.class)
public interface RbacFeignService {

    @GetMapping("/api/admin/user/{username}")
    ResponseData<UserDetailsDTO> getUserByUsername(@PathVariable("username") String username);

}
