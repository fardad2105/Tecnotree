package com.fy.tecnotreedemo;

import com.fy.tecnotreedemo.domain.comment.CommentDao;
import com.fy.tecnotreedemo.domain.post.PostDao;
import com.fy.tecnotreedemo.service.getPosts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class TecnotreeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecnotreeDemoApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("tecnotree-");
        executor.initialize();
        return executor;
    }




}
