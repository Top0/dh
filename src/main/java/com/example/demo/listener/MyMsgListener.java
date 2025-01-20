package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyMsgListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("======= {}", new String(message.getBody()));
    }
}
