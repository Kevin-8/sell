package com.zyh.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangyanghui
 * @Title WechatMpPayConfig
 * @Description
 * @date 2018/7/21 19:03
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatPayMpConfig wechatPayMpConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatPayMpConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatPayMpConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatPayMpConfig.getMchId());
        wxPayH5Config.setMchKey(wechatPayMpConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatPayMpConfig.getKeyPath());
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
}
