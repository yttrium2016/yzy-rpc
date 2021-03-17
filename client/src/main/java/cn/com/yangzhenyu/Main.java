package cn.com.yangzhenyu;

import cn.com.yangzhenyu.entity.UserEntity;
import cn.com.yangzhenyu.service.UserService;

/**
 * @author yzy
 */
public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RpcProxy rpcProxy = new RpcProxy();
        UserService userService = (UserService) rpcProxy.call(UserService.class, "localhost", 999);
        UserEntity user = userService.getUserById(1);
        System.out.println(user);

        System.out.println("时间:" + (System.currentTimeMillis() - start));
    }
}
