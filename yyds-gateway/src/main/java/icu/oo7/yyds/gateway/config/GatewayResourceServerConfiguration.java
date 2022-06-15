package icu.oo7.yyds.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * 资源服务配置类
 * @author peng.zhao
 */
@Configuration
@EnableResourceServer
// 开启注解校验权限
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class GatewayResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * TODO 令牌校验服务配置
     * 由于认证中心使用的令牌存储在内存中，因此服务端必须远程调用认证中心的校验令牌访问端点
     * 后续使用JWT令牌则本地即可进行校验，不必远程校验
     */
    @Bean
    public RemoteTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8082/oauth/check_token");
        services.setClientId("ZhiHuiWuYe");
        services.setClientSecret("123456");
        return services;
    }

    /**
     * 配置客户端惟一ID和令牌校验服务
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resources01")
                .tokenServices(tokenServices());
    }

    /**
     * 配置security的安全机制
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .anyRequest().authenticated();
    }
}
