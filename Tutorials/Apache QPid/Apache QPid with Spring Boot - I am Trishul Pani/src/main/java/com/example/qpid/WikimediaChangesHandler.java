package com.example.qpid;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

public class WikimediaChangesHandler implements EventHandler {

  private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesHandler.class.getName());

  public final JmsTemplate jmsTemplate;
  public final String queueName;

  public WikimediaChangesHandler(JmsTemplate jmsTemplate, String queueName) {
    this.jmsTemplate = jmsTemplate;
    this.queueName = queueName;
  }

  @Override
  public void onOpen() {

  }

  @Override
  public void onClosed() {

  }

  // On every new message from Wikimedia jmsTemplate sends new message to Apache QPid
  @Override
  public void onMessage(String s, MessageEvent messageEvent) {
    logger.info("Sending Message {}", messageEvent.getData());
    this.jmsTemplate.convertAndSend(queueName, messageEvent.getData());
  }

  @Override
  public void onComment(String s) {

  }

  @Override
  public void onError(Throwable throwable) {

  }
}
