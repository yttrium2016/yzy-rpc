package cn.com.yangzhenyu;

import cn.com.yangzhenyu.entity.SocketRpcRegistryEntity;
import cn.com.yangzhenyu.entity.SocketRpcServerEntity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yzy
 */
public class RegistryHandle {

    public static ConcurrentMap<String, SocketRpcServerEntity> map = new ConcurrentHashMap<>();

    public static void add(SocketRpcRegistryEntity reg){
        for (String className : reg.getClassNames()) {
            map.put(className,new SocketRpcServerEntity(className,reg.getHost(),reg.getPort()));
        }
    }

    public static SocketRpcServerEntity get(String className){
        if (map.containsKey(className)){
            return map.get(className);
        }
        return null;
    }
}
