package com.nonagon.javajourneymanbadge;

import com.nonagon.javajourneymanbadge.threads.RacerIdAssigner;
import com.nonagon.javajourneymanbadge.threads.Threads;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Configuration
@ComponentScan("com.nonagon.javajourneymanbadge")
public class ApplicationConfig {

    @Bean
    public Queue<String> dataTrack() {
        return new ArrayBlockingQueue<String>(10000);
    }

    @Bean
    public RacerIdAssigner racerIdAssigner() {
        return new RacerIdAssigner();
    }


}
