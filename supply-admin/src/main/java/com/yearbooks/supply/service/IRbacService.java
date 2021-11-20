package com.yearbooks.supply.service;

import java.util.List;

/**
 * 乐字节  踏实教育 用心服务
 *
 * @author 乐字节--老李
 * @version 1.0
 */
public interface IRbacService {
    /**
     * 根据登录用户名查询分配的角色
     * @param userName
     * @return
     */
    List<String> findRolesByUserName(String userName);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
