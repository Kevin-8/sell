package com.zyh.logout.service;

import com.zyh.logout.entity.SellerInfo;

/**
 * @author zhangyanghui
 * @Title SellerService
 * @Description
 * @date 2018/7/23 13:49
 */
public interface SellerService {

    /**
     * 卖家端
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
