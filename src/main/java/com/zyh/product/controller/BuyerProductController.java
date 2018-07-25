package com.zyh.product.controller;

import com.zyh.category.entity.ProductCategory;
import com.zyh.category.service.CategoryService;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.product.entity.ProductInfo;
import com.zyh.product.service.ProductInfoService;
import com.zyh.utils.ResultVOUtil;
import com.zyh.vo.ProductInfoVO;
import com.zyh.vo.ProductVO;
import com.zyh.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyanghui
 * @Title BuyerProductController
 * @Description 买家商品
 * @date 2018/7/17 20:35
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO productList() {
        //1.查询所有上架商品
        List<ProductInfo> productInfos = productService.findUpAll();
        //2.查询类目（一次性查询）
//        List<Integer> types=new ArrayList<>();
        //java8新特性  lambda表达式
        List<Integer> types = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(types);
        //3.数据拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOs = new ArrayList<>();
            for (ProductInfo productInfo:productInfos){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOs.add(productInfoVO);
                }
            }
            productVO.setFoods(productInfoVOs);
            productVOList.add(productVO);
        }
        ResultVO result = ResultVOUtil.success(productVOList);
//        ProductVO productVO = new ProductVO();
//        productVO.setCategoryName("新品");
//        productVO.setCategoryType(1);
//        List<ProductInfoVO> productInfoVOs = new ArrayList<>();
//        ProductInfoVO productInfoVO = new ProductInfoVO();
//        productInfoVO.setProductDescription("好好吃");
//        productInfoVO.setProductIcon("https://000.jpg");
//        productInfoVO.setProductId("1234");
//        productInfoVO.setProductName("皮皮虾");
//        productInfoVO.setProductPrice(new BigDecimal(23.4));
//        productInfoVOs.add(productInfoVO);
//        productVO.setFoods(productInfoVOs);

        return result;
    }

}
