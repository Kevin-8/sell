package com.zyh.category.dao;

import com.zyh.category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title ProductCategoryRepository
 * @Description
 * @date 2018/7/15 21:16
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
