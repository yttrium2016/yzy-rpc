package cn.com.yangzhenyu.entity;

import java.io.Serializable;

/**
 * @author yzy
 */
public class SocketRpcServerEntity implements Serializable {
    private String className;
    private String host;
    private int port;

    public SocketRpcServerEntity() {
    }

    public SocketRpcServerEntity(String className, String host, int port) {
        this.className = className;
        this.host = host;
        this.port = port;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
