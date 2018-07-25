package com.zyh.utils;

import com.zyh.enums.CodeEnum;

/**
 * @author zhangyanghui
 * @Title EnumUtil
 * @Description
 * @date 2018/7/22 15:41
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
