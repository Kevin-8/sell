package com.zyh.logout.dao;

import com.zyh.logout.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangyanghui
 * @Title SellerInfoRepository
 * @Description
 * @date 2018/7/23 0:05
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
