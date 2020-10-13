package com.springboot10task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     *
     *
     * 这个表达式依次表示 秒 分 时 天 月 和周几
     *  <p>For example, {@code "0 * * * * MON-FRI"} means once per minute on weekdays
     * 	 * (at the top of the minute - the 0th second).
     * 	 * <p>The fields read from left to right are interpreted as follows.
     * 	 * <ul>
     * 	 * <li>second</li>
     * 	 * <li>minute</li>
     * 	 * <li>hour</li>
     * 	 * <li>day of month</li>
     * 	 * <li>month</li>
     * 	 * <li>day of week</li>
     * 	 * </ul>
     */
    //别忘记在主启动类上面标注  我们尝试一下不同的表达式
    //表示每分钟启动一次
//    @Scheduled(cron = "0 * * * * MON-FRI")
    //每当遇到0,1,2,3,4执行一次
//   @Scheduled(cron = "0-4 * * * * MON-FRI")
    //每隔4秒启动一次
   @Scheduled(cron = "0/4 * * * * MON-FRI")
    public void  hello(){
        System.out.println("hello........");
    }
}
