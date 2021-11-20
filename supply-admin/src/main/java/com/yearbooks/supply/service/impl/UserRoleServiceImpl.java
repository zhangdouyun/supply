package com.yearbooks.supply.service.impl;

import com.yearbooks.supply.pojo.UserRole;
import com.yearbooks.supply.mapper.UserRoleMapper;
import com.yearbooks.supply.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Override
    public List<String> findRolesByUserName(String userName) {
        return this.baseMapper.findRolesByUserName(userName);
    }

}
