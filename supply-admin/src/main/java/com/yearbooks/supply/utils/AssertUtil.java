package com.yearbooks.supply.utils;

import com.yearbooks.supply.exceptions.ParamsException;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className AsserUtil
 * @description TODO
 * @since 2021-10-28 22:35
 */


public class AssertUtil {

    public static void isTrue(boolean flag,String msg){
        if (flag){
            throw new  ParamsException(msg);
        }
    }

}
