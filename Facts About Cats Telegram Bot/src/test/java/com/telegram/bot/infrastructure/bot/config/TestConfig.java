package com.telegram.bot.infrastructure.bot.config;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
public class TestConfig {

  @Bean
  public WireMockServer webServer() {
    final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    wireMockServer.start();
    return wireMockServer;
  }

  @Bean
  public WebClient webClient(WireMockServer server) {
    return WebClient.builder()
        .baseUrl(server.baseUrl())
        .build();
  }
}
