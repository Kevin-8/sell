package com.zyh.product.dto;

import lombok.Data;

/**
 * @author zhangyanghui
 * @Title CartDTO
 * @Description
 * @date 2018/7/20 1:16
 */
@Data
public class CartDTO {

    private String productId;
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
