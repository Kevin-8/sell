package com.zyh.order.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangyanghui
 * @Title ProductForm
 * @Description
 * @date 2018/7/22 21:40
 */
@Data
public class ProductForm {
    private String productId;
    /**商品名称**/
    private String productName;
    /**商品价格**/
    private BigDecimal productPrice;
    /**商品库存**/
    private Integer productStock;
    /**商品描述**/
    private String productDescription;
    /**商品图片**/
    private String productIcon;
    /**状态 0正常 1下架**/
    private Integer productStatus;
    /**类目编号**/
    private Integer categoryType;
}
