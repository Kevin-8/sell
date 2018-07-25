package com.zyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangyanghui
 * @Title WechatPayMpConfig
 * @Description
 * @date 2018/7/21 16:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatPayMpConfig {

    //公众号id
    private String mpAppId;
    //公众号secret
    private String mpAppSecret;
    //开发平台id
    private String openAppId;
    //开放平台secret
    private String openAppSecret;

    //商户号
    private String mchId;
    //商户秘钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
}
