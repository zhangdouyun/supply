package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.mapper.UserMapper;
import com.yearbooks.supply.pojo.User;
import com.yearbooks.supply.pojo.UserRole;
import com.yearbooks.supply.query.UserQuery;
import com.yearbooks.supply.service.IUserRoleService;
import com.yearbooks.supply.service.IUserService;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import com.yearbooks.supply.utils.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Resource
    private IUserRoleService userRoleService;

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

    /**
     * 获取用户信息实现。-实现类
     *
     * @param userQuery
     * @return
     */
    @Override
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page = new Page<User>(userQuery.getPage(), userQuery.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(userQuery.getUserName())) {
            queryWrapper.like("user_name", userQuery.getUserName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    /**
     * 用户名：1.非空；2.不可重复；3.用户密码123456；4.用户有效，setIs_del=0;
     *
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        AssertUtil.isTrue(null != this.findUserByName(user.getUsername()), "用户已经存在!");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIsDel(0);
        AssertUtil.isTrue(!this.save(user), "用户添加失败!");
        //重新查询用户记录
        User temp = this.findUserByName(user.getUsername());
        /**
         * 给用户分配角色
         */
        relationUserRole(temp.getId(), user.getRoleIds());
    }

    /**
     * 给用户分配角色
     *
     * @param userId
     * @param roleIds
     */
    private void relationUserRole(Integer userId, String roleIds) {
        /**
         * 核心表  t_user_role
         *   添加时
         *     如果角色记录存在  执行批量添加即可
         *   更新时
         *     如果用户存在原始角色记录
         *         如果存在  直接删除原来用户角色记录即可 重新添加新的用户角色记录
         *
         *         如果不存在  直接执行添加即可
         *
         *
         * 实现思路
         *    首先查询用户原始分配角色
         *       如果存在原始用户角色记录  直接删除原来用户角色记录即可（根据用户id） 然后重新添加新的用户角色记录
         *       如果不存在
         *          直接执行批量添加即可
         */
        int count = userRoleService.count(new QueryWrapper<UserRole>().eq("user_id", userId));
        if (count>0){
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id",userId))),"用户角色分配失败!");
        }
        if (StringUtils.isNotBlank(roleIds)){
            /**
             * 1.2.3.4
             */
            List<UserRole> userRoles = new ArrayList<UserRole>();
            for (String s:roleIds.split(",")){
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(s));
                userRoles.add(userRole);
            }
            AssertUtil.isTrue(!(userRoleService.saveBatch(userRoles)),"用户角色分配失败~！");
        }
    }

    /**
     * 用户名:1.非空；2.不可能重复；
     *
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        User temp = this.findUserByName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已经存在!");
        relationUserRole(user.getId(), user.getRoleIds());
        AssertUtil.isTrue(!this.updateById(user), "用户记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的记录!");
        int count = userRoleService.count(new QueryWrapper<UserRole>().in("user_id", Arrays.asList(ids)));
        if(count>0){
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().in("user_id", Arrays.asList(ids)))),
                    "用户记录删除失败!");
        }
        List<User> users = new ArrayList<User>();
        for (Integer id : ids) {
            User temp = this.getById(id);
            temp.setIsDel(1);
            users.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(users)), "用户记录删除失败!");
    }
}
