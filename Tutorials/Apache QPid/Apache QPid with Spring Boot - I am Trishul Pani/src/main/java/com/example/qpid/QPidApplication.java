package com.example.qpid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJms
@EnableScheduling
@SpringBootApplication
public class QPidApplication implements CommandLineRunner {

  private final WikimediaChangesProducer wikimediaChangesProducer;

  public QPidApplication(WikimediaChangesProducer wikimediaChangesProducer) {
    this.wikimediaChangesProducer = wikimediaChangesProducer;
  }

  public static void main(String[] args) {
    SpringApplication.run(QPidApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    wikimediaChangesProducer.sendMessage();
  }
}
