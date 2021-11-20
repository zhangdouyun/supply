package com.yearbooks.supply.service.impl;

import com.yearbooks.supply.service.IRbacService;
import com.yearbooks.supply.service.IRoleMenuService;
import com.yearbooks.supply.service.IUserRoleService;
import com.yearbooks.supply.service.IRbacService;
import com.yearbooks.supply.service.IRoleMenuService;
import com.yearbooks.supply.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 乐字节  踏实教育 用心服务
 *
 * @author 乐字节--老李
 * @version 1.0
 */
@Service
public class RbacServiceImpl  implements IRbacService {
    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> findRolesByUserName(String userName) {
        return userRoleService.findRolesByUserName(userName);
    }


    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return roleMenuService.findAuthoritiesByRoleName(roleNames);
    }
}
