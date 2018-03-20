package com.peeyush.repositories;

import com.peeyush.models.Stock;
import java.util.ArrayList;
import java.util.List;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryTest {

  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void whenGetSingleStock_thenReturnStock(){
    //given
    Money money = Money.parse("USD 23.56");
    Stock stock = new Stock("APPLE",money);
    testEntityManager.persistAndFlush(stock);
    //when
    Stock stockFound = stockRepository.findByNameIgnoreCase(stock.getName());
    //then
    Assert.assertSame(stock,stockFound);
  }

  @Test
  public void whenGetAllStocks_thenReturnAllStocks(){
    //given
    Money money = Money.parse("USD 23.56");
    Stock stock1 = new Stock("APPLE",money);
    Stock stock2 = new Stock("Google",money);
    testEntityManager.persistAndFlush(stock1);
    testEntityManager.persistAndFlush(stock2);
    //when
    Iterable<Stock> stockIterable = stockRepository.findAll();
    //then
    List<Stock> stockList    = new ArrayList<>();
    stockIterable.forEach(stockList::add);
    for(Stock s : stockList){
      if(s.getName().equalsIgnoreCase(stock1.getName())){
        Assert.assertSame(stock1,s);
      }
      if(s.getName().equalsIgnoreCase(stock2.getName())){
        Assert.assertSame(stock2,s);
      }
    }
  }

  @Test
  public void whenSaveStock_thenCreateNewStock(){
    //given
    Money money = Money.parse("USD 23.56");
    Stock stock = new Stock("APPLE",money);
    //when
    stockRepository.save(stock);
    //then
    Assert.assertSame(stock,stockRepository.findByNameIgnoreCase(stock.getName()));

  }

  @Test
  public void whenFindByNameIgnoreCase_thenReturnStockWithThatName(){
    //given
    Money money = Money.parse("USD 23.56");
    Stock stock = new Stock("APPLE",money);
    testEntityManager.persistAndFlush(stock);
    //when
    Stock foundStock = stockRepository.findByNameIgnoreCase("apple");
    //then
    Assert.assertSame(stock,foundStock);

  }

}
