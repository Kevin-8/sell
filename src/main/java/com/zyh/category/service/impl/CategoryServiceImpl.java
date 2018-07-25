package com.zyh.category.service.impl;

import com.zyh.category.dao.ProductCategoryRepository;
import com.zyh.category.entity.ProductCategory;
import com.zyh.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title CategoryServiceImpl
 * @Description
 * @date 2018/7/15 22:09
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return categoryRepository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory saveOrUpdate(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
