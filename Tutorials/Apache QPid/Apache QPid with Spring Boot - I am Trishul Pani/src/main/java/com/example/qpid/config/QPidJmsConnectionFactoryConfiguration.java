package com.example.qpid.config;

import javax.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(ConnectionFactory.class)
public class QPidJmsConnectionFactoryConfiguration {

  @Bean
  public JmsConnectionFactory jmsConnectionFactory(QPidJmsProperties properties) {
    return new QPidJmsConnectionFactory(properties).createConnectionFactory(JmsConnectionFactory.class);
  }
}
