package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-18
 */
public interface IUserRoleService extends IService<UserRole> {
    List<String> findRolesByUserName(String userName);
}
