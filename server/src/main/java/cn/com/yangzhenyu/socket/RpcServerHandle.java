package cn.com.yangzhenyu.socket;

import cn.com.yangzhenyu.ClassHandle;
import cn.com.yangzhenyu.entity.SocketRpcRequestEntity;
import cn.com.yangzhenyu.utils.StreamUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author yzy
 */
public class RpcServerHandle implements Runnable {
    private Socket socket;

    public RpcServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 主要这里处理逻辑

        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            // 1.获取客户端发送来的请求
            SocketRpcRequestEntity rpcRequest = (SocketRpcRequestEntity) StreamUtils.getObject(inputStream);
            if (rpcRequest == null) {
                throw new RuntimeException("请求参数有问题");
            }
            // 2.通过类名获取本地实现类
            Class clazz = null;
            if (ClassHandle.map.containsKey(rpcRequest.getClassName())) {
                clazz = ClassHandle.map.get(rpcRequest.getClassName());
            }
            if (clazz == null) {
                throw new RuntimeException("没有找到对应的实现类");
            }
            // 3.执行获得返回值
            Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            Object obj = method.invoke(clazz.newInstance(), rpcRequest.getArgs());

            // 4.重新发送给客户端
            StreamUtils.putObject(outputStream, obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
        }
    }
}
