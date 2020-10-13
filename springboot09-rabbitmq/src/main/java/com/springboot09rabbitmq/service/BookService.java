package com.springboot09rabbitmq.service;

import com.springboot09rabbitmq.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues="atguigu.news")//@EnableRabbit 一定要开启注解的rabbit模式
    public void listenerBook(Book book){
        System.out.println("收到消息"+book);
    }
}
