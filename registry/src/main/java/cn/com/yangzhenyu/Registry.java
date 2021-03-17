package cn.com.yangzhenyu;

import cn.com.yangzhenyu.socket.RpcRegistrySocket;

/**
 * @author yzy
 */
public class Registry {
    /**
     * 注册服务器端口
     */
    private static int regPort = 999;

    public static void main(String[] args) {
        new RpcRegistrySocket().start(regPort);
        System.out.println("注册中心启动成功");
    }

}
