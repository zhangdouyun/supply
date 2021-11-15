package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.mapper.UserMapper;
import com.yearbooks.supply.pojo.User;
import com.yearbooks.supply.query.UserQuery;
import com.yearbooks.supply.service.IUserService;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-10-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;
    /*@Override
    public User login(String userName, String password) {
        AssertUtil.isTrue(StringUtil.isEmpty(userName), "用户名不能为空!");
        AssertUtil.isTrue(StringUtil.isEmpty(password), "用户密码不能为空!");
        User user = this.findUserByName(userName);//见39行方法描述，大为简化；
        System.out.println("数据库中查到的user:" + user.toString());
        AssertUtil.isTrue(null == user, "该用户记录不存在或已注销!");
        */

    /**
     * 后续引入springSecurity 安全框架；
     *//*
        AssertUtil.isTrue(!(user.getPassword().equals(password)), "密码错误!");
        return user;
    }*/
    @Override
    public User findUserByName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del", 0).eq("user_name", userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        /**
         * 用户名：非空且唯一
         */
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()), "用户名称不能为空!");
        User temp = this.findUserByName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已经存在");
        AssertUtil.isTrue(!(this.updateById(user)), "用户信息更新失败!");
    }

    /**
     * 1.用户名非空，必须存在；
     * 2.原始密码、新密码、确认密码均不能为空；
     * 3.原始密码必须正确；
     * 4.新密码与确认密码一致，并且不能与原始密码相同；
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) {

        User user = null;
        user = this.findUserByName(userName);
        AssertUtil.isTrue(null == user, "用户不存在未未登录!");
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), "请输入原始密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), "请输入新密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), "请输入确认密码!");
        AssertUtil.isTrue(!(passwordEncoder.matches(oldPassword, user.getPassword())), "原始密码输入错误!");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码输入不一致");
        AssertUtil.isTrue(newPassword.equals(oldPassword), "新密码和原始密码不能一致");
        user.setPassword(passwordEncoder.encode(newPassword));
        AssertUtil.isTrue(!(this.updateById(user)), "用户密码更新失败!");
    }

    @Override
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page = new Page<User>(userQuery.getPage(), userQuery.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(userQuery.getUserName())) {
            queryWrapper.like("user_name", userQuery.getUserName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", page.getRecords());
        map.put("count", page.getTotal());
        return map;
    }
}
