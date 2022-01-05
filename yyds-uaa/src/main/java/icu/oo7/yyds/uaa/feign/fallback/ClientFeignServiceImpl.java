package icu.oo7.yyds.uaa.feign.fallback;

import icu.oo7.yyds.common.dto.ClientDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseCode;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.ClientFeignService;

public class ClientFeignServiceImpl implements ClientFeignService {

    public ResponseData<ClientDetailsDTO> getClientById(String clientId) {
        return ResponseData.failure(ResponseCode.RC206.getCode(), ResponseCode.RC206.getMessage());
    }

}
