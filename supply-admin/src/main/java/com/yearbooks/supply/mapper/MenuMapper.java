package com.yearbooks.supply.mapper;

import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<TreeDto> queryAllMenus();
}
