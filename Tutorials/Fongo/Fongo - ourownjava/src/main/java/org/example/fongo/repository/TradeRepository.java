package org.example.fongo.repository;

import java.util.List;
import org.example.fongo.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade, String> {

  List<Trade> findByTraderId(String traderId);

  List<Trade> findByExchangeCode(String exchangeCode);
}
