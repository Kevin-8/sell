package com.zyh.product.dao;

import com.zyh.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title ProductInfoRepository
 * @Description
 * @date 2018/7/15 22:21
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer status);


}
