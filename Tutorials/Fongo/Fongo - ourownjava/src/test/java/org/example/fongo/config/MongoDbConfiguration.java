package org.example.fongo.config;

import com.foursquare.fongo.Fongo;
import com.mongodb.Mongo;
import org.example.fongo.repository.TradeRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("org.example.fongo.repository")
@ComponentScan(basePackageClasses = {TradeRepository.class})
public class MongoDbConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "trade-db";
    }

    @Override
    public Mongo mongo() {
        return new Fongo("trade-test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.example.fongo";
    }
}