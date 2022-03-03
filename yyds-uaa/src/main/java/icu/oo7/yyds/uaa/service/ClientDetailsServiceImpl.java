package icu.oo7.yyds.uaa.service;

import icu.oo7.yyds.common.dto.ClientDetailsDTO;
import icu.oo7.yyds.common.restful.ResponseCode;
import icu.oo7.yyds.common.restful.ResponseData;
import icu.oo7.yyds.uaa.feign.ClientFeignService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private ClientFeignService clientFeignService;
    private PasswordEncoder passwordEncoder;

    /**
     * 远程加载客户端信息
     * @param clientId 客户端ID
     * @return 客户端信息
     */
    @Override
    @SneakyThrows
    public ClientDetails loadClientByClientId(String clientId) {
        ResponseData<ClientDetailsDTO> response = clientFeignService.getClientById(clientId);
        BaseClientDetails clientDetails;
        if (response.getStatus() == ResponseCode.RC200.getCode()) {
            ClientDetailsDTO clientDetailsDTO = response.getData();
            clientDetails = new BaseClientDetails(
                    clientDetailsDTO.getClientId(),
                    clientDetailsDTO.getResourceIds(),
                    clientDetailsDTO.getScope(),
                    clientDetailsDTO.getAuthorizedGrantTypes(),
                    clientDetailsDTO.getAuthorities(),
                    clientDetailsDTO.getWebServerRedirectUri()
            );
            clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
        } else {
            log.warn("ClientDetailsServiceImpl.loadClientByClientId warning occurred:[{}]-[{}]", response.getStatus(), response.getMessage());
            clientDetails = new BaseClientDetails();
        }
        return clientDetails;
    }
}