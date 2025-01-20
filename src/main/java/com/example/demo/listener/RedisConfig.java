package com.example.demo.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MyMsgListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(listener), new PatternTopic("chat.*"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MyMsgListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");
    }

    @Bean
    MyMsgListener listener() {
        return new MyMsgListener();
    }
}
