package com.zyh.category.service;

import com.zyh.category.entity.ProductCategory;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title CategoryService
 * @Description
 * @date 2018/7/15 22:07
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

    ProductCategory saveOrUpdate(ProductCategory productCategory);
}
