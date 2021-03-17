package cn.com.yangzhenyu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author yzy
 */
public class RpcServerSocket {

    ExecutorService executorService = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "mThread"));

    /**
     * 启动服务
     *
     * @param port 监听端口
     */
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                // bio 阻塞获取数据
                final Socket socket = serverSocket.accept();
                // 放到线程里处理
                executorService.execute(new RpcServerHandle(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
