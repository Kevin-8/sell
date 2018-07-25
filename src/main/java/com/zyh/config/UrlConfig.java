package com.zyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangyanghui
 * @Title UrlConfig
 * @Description
 * @date 2018/7/24 22:00
 */
@Data
@ConfigurationProperties(prefix = "urlConfig")
@Component
public class UrlConfig {

    //微信公众号平台授权url
    public String wechatMpAuthorize;
    //微信公众平台授权url
    public String wechatOpenAuthorize;
    //项目地址
    public String projectUrl;

}
