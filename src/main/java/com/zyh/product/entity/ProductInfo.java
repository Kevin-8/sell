package com.zyh.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyh.enums.ProductStatusEnum;
import com.zyh.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyanghui
 * @Title ProductInfo
 * @Description 商品表
 * @date 2018/7/15 22:17
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
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
    private Integer productStatus=ProductStatusEnum.UP.getCode();
    /**类目编号**/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
