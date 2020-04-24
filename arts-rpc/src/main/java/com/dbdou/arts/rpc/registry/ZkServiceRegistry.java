package com.dbdou.arts.rpc.registry;

import com.dbdou.arts.rpc.common.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

@Slf4j
public class ZkServiceRegistry {

    private ZkClient zkClient;

    public ZkServiceRegistry(String zkAddress) {
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
    }

    public void registry(String serviceName, String serviceAddress) {
        if (!zkClient.exists(Constant.ZK_REGISTRY_PATH)) {
            zkClient.createPersistent(Constant.ZK_REGISTRY_PATH);
        }
        String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
        }

        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        log.debug("create address node: {}", addressNode);
    }

}
