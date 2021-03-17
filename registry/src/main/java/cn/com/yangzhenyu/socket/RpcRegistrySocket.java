package cn.com.yangzhenyu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author yzy
 */
public class RpcRegistrySocket {

    ExecutorService executorService = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "mThread"));

    public void start(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                final Socket socket = serverSocket.accept();
                executorService.execute(new RpcRegistryHandle(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
