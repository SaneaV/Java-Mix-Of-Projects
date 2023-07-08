package com.example.qpid;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.EventSource.Builder;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangesProducer {

  private final String queueName;
  private final JmsTemplate jmsTemplate;

  public WikimediaChangesProducer(@Autowired JmsTemplate jmsTemplate, @Value("${spring.qpid-jms.queue}") String queueName) {
    this.jmsTemplate = jmsTemplate;
    this.queueName = queueName;
  }

  public void sendMessage() throws InterruptedException {
    final EventHandler eventHandler = new WikimediaChangesHandler(jmsTemplate, queueName);
    final String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    final EventSource.Builder builder = new Builder(eventHandler, URI.create(url));
    final EventSource eventSource = builder.build();
    eventSource.start();

    TimeUnit.MINUTES.sleep(10);
  }
}
