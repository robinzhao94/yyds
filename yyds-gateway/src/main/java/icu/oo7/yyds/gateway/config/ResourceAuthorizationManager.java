package icu.oo7.yyds.gateway.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 网关鉴权管理器
 *
 * @author peng.zhao
 */
//@Component
public class ResourceAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    public static final String JWT_TOKEN_HEADER = "Authorization";
    public static final String PERMISSION_ROLES_KEY = "auth:permission:roles";

    public static final Logger LOGGER = LoggerFactory.getLogger(ResourceAuthorizationManager.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 跨域预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // token为空直接拒绝
        if (StringUtils.isBlank(request.getHeaders().getFirst(JWT_TOKEN_HEADER))) {
            return Mono.just(new AuthorizationDecision(false));
        }
        // 移动端请求需认证但无需授权



        Map<Object, Object> permissionRoles = redisTemplate.opsForHash().entries(PERMISSION_ROLES_KEY);
        Iterator<Object> iterator = permissionRoles.keySet().iterator();
        Set<String> authorities = new HashSet<>();
        PathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getMethodValue() + "_" + request.getURI().getPath();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, path)) {
//                TODO  authorities.addAll(permissionRoles.get(pattern))
            }
        }

        // 判断JWT中携带的用户角色是否有权限访问
        return mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // roleId是请求用户的角色（格式：ROLE_{roleId}），authorities是请求资源所有需要角色的集合
                    LOGGER.info("Requested path:[{}]", path);
                    LOGGER.info("User's role:[{}]", roleId);
                    LOGGER.info("Permissions required for the resource:[{}]", authorities);
                    return authorities.contains(roleId);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
