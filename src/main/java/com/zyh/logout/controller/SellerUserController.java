package com.zyh.logout.controller;

import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.logout.entity.SellerInfo;
import com.zyh.logout.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zhangyanghui
 * @Title SellerUserController
 * @Description 卖家用户
 * @date 2018/7/24 22:06
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String,Object> map){
        //与数据库中匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(null==sellerInfo){
//            throw new SellException(ResultStatusEnum.LOGIN_ERROR);
            map.put("msg",ResultStatusEnum.LOGIN_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //设置token至redis
        //设置token至cookie
        return new ModelAndView("common/success");
    }

    @GetMapping("/logout")
    public void logout(){

    }
}
