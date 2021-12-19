package com.yearbooks.supply.service.impl;

import com.yearbooks.supply.pojo.PurchaseList;
import com.yearbooks.supply.mapper.PurchaseListMapper;
import com.yearbooks.supply.service.IPurchaseListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.DateUtil;
import com.yearbooks.supply.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {

    @Override
    public String getNextPurchaseNumber() {
        //JH20210101000x--生成该单位；
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("JH-");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            stringBuffer.append("-");
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if (null!=purchaseNumber){
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
            }else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }
}
