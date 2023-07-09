package org.example.fongo;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import org.example.fongo.config.MongoDbConfiguration;
import org.example.fongo.model.Trade;
import org.example.fongo.repository.TradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoDbConfiguration.class)
public class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    @ShouldMatchDataSet(location = "/trade-test-data.json")
    public void shouldSaveTrade() {
        tradeRepository.save(createTrade());
    }

    @Test
    @UsingDataSet(locations = "/trade-test-data.json")
    public void shouldFindByTraderId() {
        final List<Trade> trades = tradeRepository.findByTraderId("A005");
        assertNotNull(trades);
        assertTrue(trades.size() > 0);
        assertEquals("A005", trades.get(0).getTraderId());
    }

    @Test
    @UsingDataSet(locations = "/trade-test-data.json")
    public void shouldFindByExchangeCode() {
        final List<Trade> trades = tradeRepository.findByExchangeCode("NYSE");
        assertNotNull(trades);
        assertTrue(trades.size() > 0);
        assertEquals("NYSE", trades.get(0).getExchangeCode());
    }

    private Trade createTrade(){
        final Trade trade = new Trade();
        trade.setId("00001");
        trade.setTraderId("A005");
        trade.setExchangeCode("NYSE");
        trade.setValue(90.3);
        return trade;
    }
}