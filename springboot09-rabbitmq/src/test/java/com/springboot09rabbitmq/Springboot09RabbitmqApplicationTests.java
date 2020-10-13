package com.springboot09rabbitmq;

import com.springboot09rabbitmq.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot09RabbitmqApplicationTests {



    @Autowired
    RabbitTemplate rabbitTemplate;


    @Autowired
    AmqpAdmin amqpAdmin;



    @Test
    void contextLoads() {


    }



    //单拨（点对点）
    @Test
    public void send(){
        //Message需要自己构造一个;定义消息体内容和消息头  这个是要自己定义消息的
        //rabbitTemplate.send(exchage,routeKey,message);

        //如果我们不关心消息头 只关注消息体 我们就用下面这个方法
        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        //对象被默认jdk序列化以后发送出去  我们可以自定义序列化规则  我们点进源码看 看是什么对这个起了作用 我们就找到了MessageConvert
        // 发现是有一个接口 就直接ctrl+H 找到继承树  找到实际类 加在容器就行了
        // 就是我们写在配置类里面的
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);

        //甚至 我们也可以传入对象
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));


        //上面的是建立在我们已经有这些交换器和队列的前提下 如果我们没有 是在程序中临时创建的 我们就要用另外一个对象amqpAdmin

    }

    @Test
    public void recieve(){
        Object o = rabbitTemplate.convertSendAndReceive("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播  这个是不用路由表的 所有队列都会发
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }



    @Test
    public void send02(){
        //创建交换器
        amqpAdmin.declareExchange(new DirectExchange("exchange.direct"));
        //创建队列
        amqpAdmin.declareQueue(new Queue("amqp.queue"));
        //创建绑定关系
        amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,"exchange.direct","amqp.hahh",null));

        //绑定完了 接下来就是调用上面的发送了

    }

}
