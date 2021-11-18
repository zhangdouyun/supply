package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.RoleQuery;

import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 1.获取用户角色内容；
     * @param roleQuery
     * @return
     */
    Map<String, Object> roleList(RoleQuery roleQuery);

    /**
     * 1.添加角色；
     * @param role
     */
    void saveRole(Role role);

    /**
     * 通过角色名称查找角色；
     * @param roleName
     * @return
     */
    Role findRoleByRoleName(String roleName);

    /**
     * 1.修改角色；
     * @param role
     */
    void updateRole(Role role);

    /**
     * 1.删除用户；
     * @param id
     */
    void deleteRole(Integer id);

}
