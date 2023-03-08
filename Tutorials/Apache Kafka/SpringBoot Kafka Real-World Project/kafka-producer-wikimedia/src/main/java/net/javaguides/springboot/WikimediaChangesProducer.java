package net.javaguides.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.EventSource.Builder;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage() throws InterruptedException {
    final String topic = "wikimedia_recentchange";
    final EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
    final String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    final EventSource.Builder builder = new Builder(eventHandler, URI.create(url));
    final EventSource eventSource = builder.build();
    eventSource.start();

    TimeUnit.MINUTES.sleep(10);
  }
}
