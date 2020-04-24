package com.dbdou.arts.rpc.registry;

import com.dbdou.arts.rpc.common.constants.Constant;
import io.netty.util.internal.ThreadLocalRandom;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class ZkServiceDiscovery {

    private ZkClient zkClient;

    public ZkServiceDiscovery(String zkAddress) {
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
    }

    public String discover(String serviceName) {
        String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;

        if (zkClient.exists(servicePath)) {
            List<String> children = zkClient.getChildren(servicePath);
            if (children != null && children.size() > 0) {
                String randNode = children.get(ThreadLocalRandom.current().nextInt(children.size()));
                return zkClient.readData(servicePath + "/" + randNode);
            }
        }
        return null;
    }

}
