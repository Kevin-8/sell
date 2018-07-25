package com.zyh.wechat.controller;

import com.zyh.config.UrlConfig;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @author zhangyanghui
 * @Title WeChatController
 * @Description
 * @date 2018/7/21 16:20
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    @Autowired
    private UrlConfig urlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(urlConfig.getWechatMpAuthorize()
                ,WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权获取code】,result={}",redirectUrl);
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String state){
        WxMpOAuth2AccessToken accessToken=new WxMpOAuth2AccessToken();
        try{
            accessToken=wxMpService.oauth2getAccessToken(code);
            log.info("accessToken={}",accessToken);
            log.info("{}",state);
        }catch (WxErrorException e){
            log.error("【微信网页授权错误】",e);
            throw new SellException(ResultStatusEnum.WECHAT_OAUTH_ERROR.getCode(),e.getError().getErrorMsg());
        }
        log.info("url==========={}",state+"?openid="+accessToken.getOpenId());
        return "redirect:"+state+"?openid="+accessToken.getOpenId();
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl){
        String redirectUrl = wxOpenService.buildQrConnectUrl("http://zyhs.natapp1.cc/sell/wechat/qrUserInfo",
                WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权获取code】,result={}",redirectUrl);
        return "redirect:"+redirectUrl;
    }
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        WxMpOAuth2AccessToken accessToken=new WxMpOAuth2AccessToken();
        try{
            accessToken=wxOpenService.oauth2getAccessToken(code);
            log.info("accessToken={}",accessToken);
            log.info("{}",state);
        }catch (WxErrorException e){
            log.error("【微信PC网页授权错误】",e);
            throw new SellException(ResultStatusEnum.WECHAT_OAUTH_ERROR.getCode(),e.getError().getErrorMsg());
        }
        log.info("url==========={}",state+"?openid="+accessToken.getOpenId());
        return "redirect:"+state+"?openid="+accessToken.getOpenId();
    }
}
