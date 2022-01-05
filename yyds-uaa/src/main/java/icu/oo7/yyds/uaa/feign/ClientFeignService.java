package icu.oo7.yyds.uaa.feign;

import icu.oo7.yyds.common.dto.ClientDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.fallback.ClientFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "yyds-admin-clients", fallback = ClientFeignServiceImpl.class)
public interface ClientFeignService {

    @GetMapping("/api/admin/client/{clientId}")
    ResponseData<ClientDetailsDTO> getClientById(@PathVariable("clientId") String clientId);

}
