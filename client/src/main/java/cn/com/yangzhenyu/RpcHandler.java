package cn.com.yangzhenyu;

import cn.com.yangzhenyu.entity.SocketRpcRegistryEntity;
import cn.com.yangzhenyu.entity.SocketRpcRequestEntity;
import cn.com.yangzhenyu.entity.SocketRpcServerEntity;
import cn.com.yangzhenyu.utils.StreamUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author yzy
 */
public class RpcHandler implements InvocationHandler {

    private String host;
    private String className;
    private int port;

    public <T> RpcHandler(String className, String host, int port) {
        this.host = host;
        this.port = port;
        this.className = className;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 从注册中心获取请求服务器
        SocketRpcRegistryEntity reg = new SocketRpcRegistryEntity();
        reg.setType(1);
        reg.setServiceClassName(className);

        Socket socket = new Socket(host, port);

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        ObjectOutputStream rpcOutputStream = null;
        ObjectInputStream rpcInputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            StreamUtils.putObject(outputStream, reg);

            inputStream = new ObjectInputStream(socket.getInputStream());
            SocketRpcServerEntity rpcServer = (SocketRpcServerEntity) StreamUtils.getObject(inputStream);
            if (rpcServer == null) {
                throw new RuntimeException("没有获取到对应服务器");
            }

            // 远程调用服务区
            Socket socketRpc = new Socket(rpcServer.getHost(), rpcServer.getPort());
            rpcOutputStream = new ObjectOutputStream(socketRpc.getOutputStream());
            SocketRpcRequestEntity entity = new SocketRpcRequestEntity();
            entity.setArgs(args);
            entity.setClassName(className);
            entity.setMethodName(method.getName());
            entity.setTypes(method.getParameterTypes());

            StreamUtils.putObject(rpcOutputStream, entity);

            rpcInputStream = new ObjectInputStream(socketRpc.getInputStream());

            return StreamUtils.getObject(rpcInputStream);

        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
            StreamUtils.close(rpcInputStream);
            StreamUtils.close(rpcOutputStream);
        }


    }
}
