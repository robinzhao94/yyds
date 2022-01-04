package icu.oo7.yyds.uaa.service;

import lombok.SneakyThrows;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 客户端认证信息接口
 * @author peng.zhao
 */
public class JdbcClientDetailsServiceImpl extends JdbcClientDetailsService {

    public JdbcClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @SneakyThrows
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
