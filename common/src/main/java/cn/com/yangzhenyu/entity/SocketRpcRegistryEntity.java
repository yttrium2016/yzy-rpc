package cn.com.yangzhenyu.entity;

import java.io.Serializable;

/**
 * @author yzy
 */
public class SocketRpcRegistryEntity implements Serializable {

    /**
     * 类型 0 注册 1获取
     */
    private int type;

    private String host;
    private int port;
    private String serviceClassName;
    private String[] classNames;

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

    public String[] getClassNames() {
        return classNames;
    }

    public void setClassNames(String[] classNames) {
        this.classNames = classNames;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }
}
