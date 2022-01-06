package com.yearbooks.supply.utils;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className StringUtil
 * @description TODO
 * @since 2021-10-28 22:52
 */


public class StringUtil {

    /**
     * 判断是否是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 生成四位编号
     * @param code
     * @return
     */
    public static String formatCode(String code){/*注意：code为参数名称，类型为string*/
        try {
            int length = code.length();
            Integer num = Integer.valueOf(code.substring(length-4,length))+1;
            String codenum = num.toString();
            int codelength = codenum.length();
            for (int i = 4; i > codelength; i--) {
                codenum = "0"+codenum;
            }
            return codenum;
        } catch (NumberFormatException e) {
            return "0100";
        }
    }
}
