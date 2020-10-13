package com.vanward.starter.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="vanward.hello")
public class HelloProperties {

    private String prefix="你好";
    private String suffix="世界";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}