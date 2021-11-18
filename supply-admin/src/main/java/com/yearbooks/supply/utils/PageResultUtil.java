package com.yearbooks.supply.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className PageResultUtil
 * @description TODO
 * @since 2021-11-18 19:45
 */
public class PageResultUtil {

    public static Map<String,Object> getResult(Long total, List<?> records){
        Map<String,Object> result =new HashMap<String,Object>();
        result.put("count",total);
        result.put("data",records);//注意：是data，非date。
        result.put("code",0);
        result.put("msg","");
        return result;
    }
}
