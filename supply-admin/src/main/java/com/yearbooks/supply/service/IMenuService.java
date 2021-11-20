package com.yearbooks.supply.service;

import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-20
 */
public interface IMenuService extends IService<Menu> {

    List<TreeDto> queryAllMenus(Integer roleId);

    Map<String, Object> menuList();

    void saveMenu(java.awt.Menu menu);

    void updateMenu(java.awt.Menu menu);

    void deleteMenuById(Integer id);
}
