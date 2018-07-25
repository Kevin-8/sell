package com.zyh.product.controller;

import com.zyh.category.entity.ProductCategory;
import com.zyh.category.service.CategoryService;
import com.zyh.enums.ProductStatusEnum;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.order.form.ProductForm;
import com.zyh.order.service.BuyerService;
import com.zyh.product.entity.ProductInfo;
import com.zyh.product.service.ProductInfoService;
import com.zyh.utils.KeyUtil;
import com.zyh.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyanghui
 * @Title SellerProductController
 * @Description
 * @date 2018/7/22 18:08
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView getProductList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",defaultValue = "5") Integer size,
                                       Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> productInfos = productInfoService.findUpAll(pageRequest);
        map.put("productInfo",productInfos);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/list",map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSaleProduct(@RequestParam("productId") String productId,
                                      Map<String,Object> map){
        map.put("url","/sell/seller/product/list");
        try{
            productInfoService.onSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            return new ModelAndView("/common/error",map);
        }
        map.put("msg", ResultStatusEnum.SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);

    }
    @GetMapping("/off_sale")
    public ModelAndView offSaleProduct(@RequestParam("productId") String productId,
                                       Map<String,Object> map){
        map.put("url","/sell/seller/product/list");
        try{
            productInfoService.offSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            return new ModelAndView("/common/error",map);
        }
        map.put("msg", ResultStatusEnum.SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }

    /**
     * 新增商品和更新
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String,Object> map){
        //如果不为空做更新操作
        if(StringUtils.isNoneBlank(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有类目
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("productCategory",productCategories);
        return new ModelAndView("/product/index",map);
    }

    /**
     * 保存更新
     * @param productForm
     * @param result
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView saveProduct(@Valid ProductForm productForm,
                                    BindingResult result,
                                    Map<String,Object> map){
        if(result.hasErrors()){
            map.put("msg",result.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
            //新增商品
            if(StringUtils.isNotEmpty(productForm.getProductId())){
                //修改商品
                productInfo=productInfoService.findOne(productForm.getProductId());
                productForm.setProductStatus(productInfo.getProductStatus());
            }else{
                productForm.setProductStatus(ProductStatusEnum.UP.getCode());
                productForm.setProductId(TextUtil.joinString(KeyUtil.getRandomString(4)));
            }

            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.saveOrUpdate(productInfo);
        } catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("msg",ResultStatusEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/common/success",map);
    }

}
