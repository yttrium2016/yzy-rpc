package cn.com.yangzhenyu.service.impl;

import cn.com.yangzhenyu.entity.UserEntity;
import cn.com.yangzhenyu.service.UserService;

/**
 * @author yzy
 */
public class UserServiceImpl implements UserService {
    @Override
    public UserEntity getUserById(int id) {
        return new UserEntity(id, "yzy");
    }
}
