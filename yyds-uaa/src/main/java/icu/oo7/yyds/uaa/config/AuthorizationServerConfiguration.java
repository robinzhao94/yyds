package icu.oo7.yyds.uaa.config;

import icu.oo7.yyds.common.dto.UserDetailsDTO;
import icu.oo7.yyds.common.util.constant.AuthContants;
import icu.oo7.yyds.uaa.service.UserDetailsServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认证配置
 * @author peng.zhao
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public ClientDetailsService clientDetailsService;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        // 读取客户端配置
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置授权服务器端点的非安全功能，如令牌存储、令牌自定义、用户批准和授权类型
     */
    @Override
    @SneakyThrows
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        // 配置授权服务器端点的属性和增强功能
        endpoints
                 // 用于密码认证的 AuthenticationManager。
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                 // token 增强配置
                .tokenEnhancer(tokenEnhancerChain)
                 // 加载用户特定数据的核心接口
                .userDetailsService(userDetailsService)
                // refresh token 有两种使用方式：重复使用-true、非重复使用-false，默认-true
                // 1.重复使用：access token 过期刷新时，refresh token 过期时间未改变，仍以初次生成的时间为准
                // 2.非重复使用：access token 过期刷新时，refresh token 过期时间延续，在 refresh token 有效期内刷新便永不失效达到无需再次登录的目的
                .reuseRefreshTokens(true);
    }

    /**
     * 扩展JWT存储内容
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken,  authentication) -> {
            Map<String, Object> additionalInformation = new HashMap<>();
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO)authentication.getUserAuthentication().getPrincipal();
            additionalInformation.put(AuthContants.JWT_USER_ID_KEY, userDetailsDTO.getId());
            additionalInformation.put(AuthContants.JWT_USER_NAME_KEY, userDetailsDTO.getUsername());
            additionalInformation.put(AuthContants.JWT_CLIENT_ID_KEY, userDetailsDTO.getClientId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            return accessToken;
        };
    }

    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 从classpath下的密钥库中获取密钥对
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("yyds.jks"), "4rfv#EDC".toCharArray());
        return factory.getKeyPair("yyds", "2wsx#EDC".toCharArray());
    }
}
