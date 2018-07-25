package com.zyh.category.dao;

import com.zyh.category.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyanghui
 * @Title ProductCategoryRepositoryTest
 * @Description
 * @date 2018/7/15 21:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory=repository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest(){
//        ProductCategory productCategory=new ProductCategory();
//        productCategory.setCategoryName("网友力荐");
//        productCategory.setCategoryType(3);
        ProductCategory productCategory=new ProductCategory("新品推荐",4);
//        repository.save(productCategory);
        ProductCategory result=new ProductCategory();
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null,productCategory);
    }
    @Test
    public void updateTest(){
        ProductCategory productCategory=repository.findOne(1);
        productCategory.setCategoryType(1);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTet(){
        List<Integer> list= Arrays.asList(1,2,3,4);
        List<ProductCategory> productCategories=repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategories.size());
    }

}