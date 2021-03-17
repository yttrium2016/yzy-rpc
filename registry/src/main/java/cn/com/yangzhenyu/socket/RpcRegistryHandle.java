package cn.com.yangzhenyu.socket;

import cn.com.yangzhenyu.RegistryHandle;
import cn.com.yangzhenyu.entity.SocketRpcRegistryEntity;
import cn.com.yangzhenyu.entity.SocketRpcServerEntity;
import cn.com.yangzhenyu.utils.StreamUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author yzy
 */
public class RpcRegistryHandle implements Runnable {
    private Socket socket;

    public RpcRegistryHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 获取客户端发来的消息
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            SocketRpcRegistryEntity reg = (SocketRpcRegistryEntity) StreamUtils.getObject(inputStream);

            if (reg == null) {
                throw new RuntimeException("发送信息体有问题");
            }
            if (reg.getType() == 0) {
                // 注册
                RegistryHandle.add(reg);
                StreamUtils.putObject(outputStream, reg.getHost() + ":" + reg.getPort() + "服务器注册成功");
                System.out.println(reg.getHost() + ":" + reg.getPort() + "服务器注册成功");
            } else if (reg.getType() == 1) {
                // 获取服务器
                SocketRpcServerEntity entity = RegistryHandle.get(reg.getServiceClassName());
                StreamUtils.putObject(outputStream, entity);
                System.out.println("获取服务器成功");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
        }

    }
}
