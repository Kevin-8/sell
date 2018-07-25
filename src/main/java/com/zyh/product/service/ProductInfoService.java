package com.zyh.product.service;

import com.zyh.product.dto.CartDTO;
import com.zyh.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title ProductInfoService
 * @Description
 * @date 2018/7/17 19:26
 */
public interface ProductInfoService {

    ProductInfo findOne(String id);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findUpAll(Pageable pageable);

    ProductInfo saveOrUpdate(ProductInfo product);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架商品
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架商品
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
