package com.example.qpid.config;

import javax.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureBefore(JmsAutoConfiguration.class)
@AutoConfigureAfter({JndiConnectionFactoryAutoConfiguration.class})
@ConditionalOnClass({ConnectionFactory.class, JmsConnectionFactory.class})
@ConditionalOnMissingBean(ConnectionFactory.class)
@EnableConfigurationProperties(QPidJmsProperties.class)
@Import(QPidJmsConnectionFactoryConfiguration.class)
public class QPidJmsAutoConfiguration {

}
