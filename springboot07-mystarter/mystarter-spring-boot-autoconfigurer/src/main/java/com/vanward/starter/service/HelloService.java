package com.vanward.starter.service;

public class HelloService {



    //添加一个属性类 这个属性类需要被注入
    private  HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    //定义一个方法 帮我们做事
    public String sayHelloService(String name){

        return helloProperties.getPrefix()+"==="+name+"==="+helloProperties.getSuffix();
    }
}