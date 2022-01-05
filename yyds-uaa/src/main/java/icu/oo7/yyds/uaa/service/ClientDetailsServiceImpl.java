package icu.oo7.yyds.uaa.service;

import icu.oo7.yyds.common.dto.ClientDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseCode;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.ClientFeignService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * 客户端认证信息接口
 * @author peng.zhao
 */
@Service
@AllArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private ClientFeignService clientFeignService;
    private PasswordEncoder passwordEncoder;

    /**
     * 远程加载客户端信息
     * @param clientId 客户端ID
     * @return 客户端信息
     */
    @SneakyThrows
    public ClientDetails loadClientByClientId(String clientId) {
        ResponseData<ClientDetailsDTO> response = clientFeignService.getClientById(clientId);
        // TODO 客户端认证信息查询
        if (response.getStatus() == ResponseCode.RC200.getCode()) {
            ClientDetailsDTO clientDetailsDTO = response.getData();
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
            return clientDetails;
        } else {
            // TODO 客户端认证信息查询失败
            return null;
        }
    }
}