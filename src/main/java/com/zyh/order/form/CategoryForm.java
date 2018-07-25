package com.zyh.order.form;

import lombok.Data;

/**
 * @author zhangyanghui
 * @Title CategoryForm
 * @Description
 * @date 2018/7/22 23:06
 */
@Data
public class CategoryForm {
    private Integer categoryId;
    /**类目名字**/
    private String categoryName;
    /**类目编号**/
    private Integer categoryType;
}
