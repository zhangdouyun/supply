package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.Role;
import com.yearbooks.supply.mapper.RoleMapper;
import com.yearbooks.supply.query.RoleQuery;
import com.yearbooks.supply.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 1.实现类--获取用户角色内容
     * @param roleQuery
     * @return
     */
    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(roleQuery.getRoleName())){
            queryWrapper.like("name",roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    /**
     * 2.添加角色；
     * @param role
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名!");
        AssertUtil.isTrue(null!=this.findRoleByRoleName(role.getName()),"角色名已存在!");
        role.setIsDel(0);
        AssertUtil.isTrue(!this.save(role),"角色添加失败!");
    }

    /**
     * 3.通过角色名称查找角色；
     * @param roleName
     * @return
     */
    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().eq("is_del",0).eq("name",roleName));
    }

    /**
     * 4.更新角色信息
     * @param role
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入用户角色!");
        Role temp = this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null!=temp && !(temp.getId().equals(role.getId())),"角色名称已经存在!");
        AssertUtil.isTrue(this.updateById(role),"角色更新失败!");
    }

    /**
     * 5.删除角色信息
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        AssertUtil.isTrue(null==id,"请选择待删除的记录!");
        Role role = this.getById(id);
        AssertUtil.isTrue(null==role,"待删除记录不存在!");
        role.setIsDel(1);
        AssertUtil.isTrue(!this.updateById(role),"角色删除失败!");
    }
}
