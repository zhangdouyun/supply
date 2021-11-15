package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.UserQuery;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-10-27
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录方法接口
     * @param userName
     * @param password
     * @return
     */
    //User login(String userName, String password);

    /**
     * 根据用户名查询用户记录；
     * @param userName
     * @return
     */
    public User findUserByName(String userName);

    /**
     * 用户信息更新
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 用户密码更新操作
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);

    Map<String, Object> userList(UserQuery userQuery);
}
