package net.javaguides.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootProducerApplication implements CommandLineRunner {

  private final WikimediaChangesProducer wikimediaChangesProducer;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootProducerApplication.class);
  }

  @Override
  public void run(String... args) throws Exception {
    wikimediaChangesProducer.sendMessage();
  }
}
