package com.zyh.logout.service.impl;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.logout.dao.SellerInfoRepository;
import com.zyh.logout.entity.SellerInfo;
import com.zyh.logout.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangyanghui
 * @Title SellerServiceImpl
 * @Description
 * @date 2018/7/23 13:50
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        if(null==sellerInfo){
            throw new SellException(ResultStatusEnum.SELLER_INFO_NOT_EXIST);
        }
        return sellerInfo;
    }
}
