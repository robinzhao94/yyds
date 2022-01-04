package icu.oo7.yyds.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("yyds-admin")
public interface UserService {
}
