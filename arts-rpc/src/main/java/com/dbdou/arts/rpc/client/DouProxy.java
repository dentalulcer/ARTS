package com.dbdou.arts.rpc.client;

import com.dbdou.arts.rpc.common.bean.DouRequest;
import com.dbdou.arts.rpc.common.bean.DouResponse;
import com.dbdou.arts.rpc.registry.ZkServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.UUID;

@Slf4j
public class DouProxy {

    private String serviceAddress;

    private ZkServiceDiscovery discovery;

    public DouProxy(ZkServiceDiscovery discovery) {
        this.discovery = discovery;
    }

    public <T> T create(final Class<?> interfaceClass) {
        return create(interfaceClass, "");
    }

    public <T> T create(final Class<?> interfaceClass, final String serviceVersion) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    DouRequest request = new DouRequest();
                    request.setRequestId(UUID.randomUUID().toString());
                    request.setInterfaceName(method.getDeclaringClass().getName());
                    request.setServiceVersion(serviceVersion);
                    request.setMethodName(method.getName());
                    request.setParameterTypes(method.getParameterTypes());
                    request.setParameters(args);

                    // 获取 RPC 服务地址
                    if (discovery != null) {
                        String serviceName = interfaceClass.getName();
                        if (!"".equals(serviceVersion)) {
                            serviceName += "-" + serviceVersion;
                        }
                        serviceAddress = discovery.discover(serviceName);
                        log.debug("discover service: {} => {}", serviceName, serviceAddress);
                    }
                    // 从 RPC 服务地址中解析主机名与端口号
                    String[] array = serviceAddress.split(":");
                    String host = array[0];
                    int port = Integer.parseInt(array[1]);
                    // 创建 RPC 客户端对象并发送 RPC 请求
                    DouClient client = new DouClient(host, port);
                    long time = System.currentTimeMillis();
                    DouResponse response = client.send(request);
                    log.debug("time: {}ms", System.currentTimeMillis() - time);
                    if (response == null) {
                        throw new RuntimeException("response is null");
                    }
                    // 返回 RPC 响应结果
                    if (response.getException() != null) {
                        throw response.getException();
                    } else {
                        return response.getResult();
                    }
                });
    }

}
