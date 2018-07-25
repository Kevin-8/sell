package com.zyh.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangyanghui
 * @Title WechatOpenConfig
 * @Description
 * @date 2018/7/23 14:24
 */
@Component
public class WechatOpenConfig {

    @Autowired
    private WechatPayMpConfig wechatPayMpConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxOpenInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxOpenInMemoryConfigStorage.setAppId(wechatPayMpConfig.getOpenAppId());
        wxOpenInMemoryConfigStorage.setSecret(wechatPayMpConfig.getOpenAppSecret());
        return wxOpenInMemoryConfigStorage;
    }

}
