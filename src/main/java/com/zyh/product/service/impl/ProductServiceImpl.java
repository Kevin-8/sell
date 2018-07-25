package com.zyh.product.service.impl;

import com.zyh.enums.ProductStatusEnum;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.product.dao.ProductInfoRepository;
import com.zyh.product.dto.CartDTO;
import com.zyh.product.entity.ProductInfo;
import com.zyh.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title ProductServiceImpl
 * @Description
 * @date 2018/7/17 19:29
 */
@Service
public class ProductServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productRepository;

    @Override
    public ProductInfo findOne(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductInfo saveOrUpdate(ProductInfo product) {
        return productRepository.save(product);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productRepository.findOne(cartDTO.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productRepository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cart : cartDTOList) {
            ProductInfo productInfo = productRepository.findOne(cart.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cart.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultStatusEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productRepository.findOne(productId);
        if(null==productInfo){
            throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatusEnum.UP==productInfo.getProductStatusEnum()){
            throw new SellException(ResultStatusEnum.PRODUCT_STATUS_ERROR);

        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productRepository.findOne(productId);
        if(null==productInfo){
            throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatusEnum.DOWN==productInfo.getProductStatusEnum()){
            throw new SellException(ResultStatusEnum.PRODUCT_STATUS_ERROR);

        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(productInfo);
    }
}
