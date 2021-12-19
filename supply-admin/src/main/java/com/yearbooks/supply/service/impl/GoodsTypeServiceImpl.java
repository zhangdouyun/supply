package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.pojo.GoodsType;
import com.yearbooks.supply.mapper.GoodsTypeMapper;
import com.yearbooks.supply.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Override
    public List<TreeDto> queryAllGoodsTypes() {
        return this.baseMapper.queryAllGoodsTypes();
    }

    @Override
    public List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId) {
        GoodsType goodsType = this.getById(typeId);
        if (goodsType.getId()==-1){
            //所有类别
            return this.list().stream().map(GoodsType::getId).collect(Collectors.toList());
        }
        List<Integer> result = new ArrayList<Integer>();
        result.add(typeId);
        return getSubTypeIds(typeId,result);
    }

    @Override
    public Map<String, Object> goodsTypeList() {
        List<GoodsType> menus = this.list();
        return PageResultUtil.getResult(((long) menus.size()),menus);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveGoodsType(GoodsType goodsTpye) {
        /**
         * 1.商品类别不能为空；
         * 2.商品类别上级id非空；
         * 3.考虑父类别（父类别本身state=0）
         *    设置父类别state=1；
         */
        AssertUtil.isTrue(StringUtils.isBlank(goodsTpye.getName()),"商品类别名称不能为空!");
        AssertUtil.isTrue(null==goodsTpye.getpId(),"请指定父类别!");
        goodsTpye.setState(0);
        AssertUtil.isTrue(!(this.save(goodsTpye)),"记录添加失败!");
        GoodsType parent = this.getById(goodsTpye.getpId());
        if (parent.getState()==0){
            parent.setState(1);
        }
        AssertUtil.isTrue(!(this.updateById(parent)),"记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteGoodsType(Integer id) {
        /**
         * 1.删除记录必须存在；
         * 2.如果类别下存在子类别不允许删除；
         * 3.如果节点删除，上级节点没有子节点，此时设置上级节点为0；
         */
        GoodsType temp = this.getById(id);
        AssertUtil.isTrue(null==temp,"待删除的节点不存在!");
        int count = this.count(new QueryWrapper<GoodsType>().eq("p_id", id));
        AssertUtil.isTrue(count>0,"存在子类别，暂不支持级联删除!");
        count = this.count(new QueryWrapper<GoodsType>().eq("p_id", temp.getpId()));
        if (count>1){
            AssertUtil.isTrue(!(this.update(new UpdateWrapper<GoodsType>().set("state",0).eq("id",temp.getpId()))),"类别删除失败!");
        }
        AssertUtil.isTrue(!(this.removeById(id)),"类别删除失败!");
    }

    private List<Integer> getSubTypeIds(Integer typeId, List<Integer> result) {
        List<GoodsType> goodsTypes = this.baseMapper.selectList(new QueryWrapper<GoodsType>().eq("p_id", typeId));

        if (CollectionUtils.isNotEmpty(goodsTypes)){
            goodsTypes.forEach(gt -> {
                result.add(gt.getId());
                getSubTypeIds(gt.getId(),result);
            });
        }
        return result;
    }
}
