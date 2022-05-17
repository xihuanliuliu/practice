package com.jd.edi.version5.register;

import com.jd.edi.version5.loadbalance.LoadBalance;
import com.jd.edi.version5.loadbalance.RoundLoadBalance;
import org.apache.curator.RetryLoop;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;
import java.util.List;

public class ZkServiceRegister implements ServiceRegister{
    // curator提供的zk客户端
    private CuratorFramework client;
    // zk的根路径接节点
    private static final String ROOT_PATH = "myRPC";
    private LoadBalance loadBalance = new RoundLoadBalance();
    public ZkServiceRegister(String connectionAddress, String rootPath) {
        // 指数时间重试
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        // zk的地址是固定的，消费者，提供者都需要与zk建立链接
        // 使用心跳监听状态
        this.client = CuratorFrameworkFactory.builder().connectString(connectionAddress)
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(rootPath).build();
        this.client.start();
        System.out.println("zk链接成功");
    }

    @Override
    public void register(String serviceName, InetSocketAddress serverAddress) {
        try {
            // serviceName创建成永久节点，服务提供者下线时，不删除服务名，只删除地址
            if (client.checkExists().forPath("/" + serviceName) == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + serviceName);
            }
            // 路径地址： 一个/表示一个节点
            String path = "/" + serviceName + "/" + getServiceAddress(serverAddress);
            // 临时节点，服务器下线就删除节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            System.out.println("此服务已经存在");
        }
    }


    @Override
    public InetSocketAddress discoveryService(String serviceName) {
        try {
            List<String> strings = client.getChildren().forPath("/" + serviceName);
            // 负载均衡
            String address = loadBalance.balance(strings);

            return parseAddress(address);
        } catch (Exception e) {

        }
        return null;
    }

    // 地址： ->  xxx.xxx.xxxx.xxx:port
    private String getServiceAddress(InetSocketAddress inetSocketAddress) {
        return inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort();
    }

    // 字符串转为地址
    private InetSocketAddress parseAddress(String address) {
        String[] substring = address.split(":");
        return new InetSocketAddress(substring[0], Integer.parseInt(substring[1]));
    }
}
