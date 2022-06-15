package icu.oo7.yyds.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌配置类
 * @author peng.zhao
 */
@Configuration
public class TokenConfiguration {

    /**
     * TODO 暂定使用内存存储令牌，比较常用的令牌存储方式则是redis、JWT
     */
    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
