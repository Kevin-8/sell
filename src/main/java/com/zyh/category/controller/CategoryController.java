package com.zyh.category.controller;

import com.zyh.category.entity.ProductCategory;
import com.zyh.category.service.CategoryService;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.order.form.CategoryForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyanghui
 * @Title CategoryController
 * @Description
 * @date 2018/7/22 22:41
 */
@Controller
@RequestMapping("/seller/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView getCategoryList(Map<String,Object> map){
        List<ProductCategory> categories = categoryService.findAll();
        map.put("productCategory", categories);
        return new ModelAndView("/category/list",map);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId!=null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }
//        List<ProductCategory> categories=categoryService.findAll();
//        map.put("productCategory",categories);

        return new ModelAndView("/category/index",map);
    }
    @PostMapping("/save")
    public ModelAndView saveCategory(@Valid CategoryForm categoryForm,
                                     BindingResult result,
                                     Map<String,Object> map){
        if(result.hasErrors()){
            map.put("msg",result.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("/common/error",map);
        }
        ProductCategory category = new ProductCategory();
        try{
            if(null!=categoryForm.getCategoryId()){
                category=categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,category);
            categoryService.saveOrUpdate(category);
            map.put("msg",ResultStatusEnum.SUCCESS.getMessage());
            map.put("url","/sell/seller/category/list");
        } catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("/common/error",map);
        }

        return new ModelAndView("/common/success",map);
    }

}
