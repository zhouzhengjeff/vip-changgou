package com.hnguigu.changgou.filter;

import com.alibaba.fastjson.JSON;
import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class IpFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String hostName = request.getRemoteAddress().getHostName();
        if (hostName.contains("127")) {

            Result result = new Result<>(false, StatusCode.FORBIDDEN, "IP" +
                    "地址非法，不允许访问", null);
            String resultJSONString = JSON.toJSONString(result);
            // TODO 将JSON字符串传输到前端

            response.setComplete();
        }

        return chain.filter(exchange);
    }
}
