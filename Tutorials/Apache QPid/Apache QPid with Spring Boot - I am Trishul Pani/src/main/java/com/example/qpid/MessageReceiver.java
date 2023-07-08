package com.example.qpid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

  private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class.getName());

  // JMS listens specific queue and returns messages
  @JmsListener(destination = "queue")
  public void processMessage(String message) {
    logger.info("Received message : {}", message);
  }
}
