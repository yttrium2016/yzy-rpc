package cn.com.yangzhenyu;

import cn.com.yangzhenyu.entity.SocketRpcRegistryEntity;
import cn.com.yangzhenyu.service.impl.UserServiceImpl;
import cn.com.yangzhenyu.socket.RpcServerSocket;
import cn.com.yangzhenyu.utils.StreamUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author yzy
 */
public class Server {
    /**
     * 注册服务器地址
     */
    private static String regHost = "localhost";
    /**
     * 注册服务器端口
     */
    private static int regPort = 999;

    /**
     * 本地服务端口
     */
    private static String localHost = "localhost";

    /**
     * 本地服务端口
     */
    private static int localPort = 888;

    public static void main(String[] args) {
        // 向服务器注册
        SocketRpcRegistryEntity reg = new SocketRpcRegistryEntity();
        reg.setClassNames(new String[]{"cn.com.yangzhenyu.service.UserService"});
        reg.setHost(localHost);
        reg.setPort(localPort);
        reg.setType(0);

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            Socket socket = new Socket(regHost, regPort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            StreamUtils.putObject(outputStream, reg);

            String msg = (String) StreamUtils.getObject(inputStream);
            System.out.println(msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(outputStream);
            StreamUtils.close(outputStream);
        }

        // 本地注册映射关系
        ClassHandle.map.put("cn.com.yangzhenyu.service.UserService", UserServiceImpl.class);

        // 启动本地服务监听
        System.out.println("启动成功");
        new RpcServerSocket().start(localPort);
    }
}
