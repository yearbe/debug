package com.debug.demo.filter;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.debug.demo.config.DynamicDataSourceKeys;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Lyb
 * @since 2020-01-20
 */
@Component
public class DynamicDataSourceFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response =  serverWebExchange.getResponse();

        String tokenKey = "token";
        if (!request.getHeaders().containsKey(tokenKey)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response.writeWith(Mono.just(response.bufferFactory().wrap("{\"msg\":\"No token.\"}".getBytes())));
        }

        String token = request.getHeaders().getFirst(tokenKey);
        String dbKey = "db" + token;
        if (!DynamicDataSourceKeys.existKey(dbKey)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response.writeWith(Mono.just(response.bufferFactory().wrap("{\"msg\":\"Incorrect token.\"}".getBytes())));
        }

        DynamicDataSourceContextHolder.push(dbKey);
        return webFilterChain.filter(serverWebExchange);
    }
}
