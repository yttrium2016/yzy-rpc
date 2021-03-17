package cn.com.yangzhenyu.service;


import cn.com.yangzhenyu.entity.UserEntity;

/**
 * @author yzy
 */
public interface UserService {

    /**
     * 获取用户通过ID
     * @param id 用户ID
     * @return 返回用户
     */
    UserEntity getUserById(int id);
}
