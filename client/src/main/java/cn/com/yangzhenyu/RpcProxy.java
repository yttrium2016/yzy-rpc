package cn.com.yangzhenyu;

import cn.com.yangzhenyu.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yzy
 */
public class RpcProxy<T> {
    public T call(Class<T> clazz, String host, int port) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcHandler(clazz.getName(), host, port));
    }
}
