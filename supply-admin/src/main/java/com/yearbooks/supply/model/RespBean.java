package com.yearbooks.supply.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className RespBean
 * @description TODO
 * @since 2021-10-28 21:54
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    @ApiModelProperty(value = "响应状态")
    private long code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应结果信息")
    private Object obj;


    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return  new RespBean(500,message,null);
    }

}
