package com.peeyush.services;


import com.peeyush.exceptions.StockAlreadyExistsException;
import com.peeyush.exceptions.StockMissingInformationException;
import com.peeyush.exceptions.StockNotFoundException;
import com.peeyush.models.Stock;
import com.peeyush.repositories.StockRepository;
import com.peeyush.service.StockService;
import com.peeyush.service.StockServiceImpl;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StockServiceImplTest {

  @TestConfiguration
  static class StockServiceImplTestContextConfiguration {
    @Bean
    public StockService stockService(){
      return new StockServiceImpl();
    }

  }
  @Autowired
  private StockService stockService;
  @MockBean
  private StockRepository stockRepository;
  @MockBean
  private Stock mockStock;


  @Test
  public void whenGetSingleStocks_thenGetThatStocks(){
    //given
    Money money = Money.parse("USD 23.47");
    Stock stock = new Stock("APPLE",money);
    Optional<Stock> optional = Optional.of(stock);
    Mockito.when(stockRepository.findById(MockitoHamcrest.longThat(Matchers.any(Long.class)))).thenReturn(optional);
    //when
    Stock stock1 = stockService.getSingleStock((long)123);
    //then
    Assert.assertSame(stock,stock1);
    Mockito.verify(stockRepository,Mockito.times(1)).findById((long)123);
  }

  @Test(expected = StockNotFoundException.class)
  public void whenGetSingleStockNotFound_thenGetStockNotFoundException(){
    Optional<Stock> optional = Optional.ofNullable(null);
    Mockito.when(stockRepository.findById(MockitoHamcrest.longThat(Matchers.any(Long.class)))).thenReturn(optional);
    stockService.getSingleStock((long)123);
  }

  @Test(expected = StockAlreadyExistsException.class)
  public void whenCreateStockUsingExistingStockName_thenGetStockAlreadyExistsException(){
    Money money               = Money.parse("USD 23.47");
    Stock stockToBeCreated    = new Stock("APPLE",money);
    Stock existingStock       = new Stock("APPLE",money);
    Mockito.when(stockRepository.findByNameIgnoreCase(MockitoHamcrest.argThat(Matchers.any(String.class)))).thenReturn(existingStock);
    stockService.createNewStock(stockToBeCreated);
  }

  @Test(expected = StockMissingInformationException.class)
  public void whenCreateStockUsingMissingInformation_thenGetStockMissingInformationException(){
    Mockito.when(mockStock.getName()).thenReturn(null);
    stockService.createNewStock(mockStock);
  }

  @Test
  public void whenCreateStock_thenSaveStock(){
    //given
    Money money               = Money.parse("USD 23.47");
    Stock stockToBeCreated    = new Stock("APPLE",money);
    Mockito.when(stockRepository.findByNameIgnoreCase("APPLE")).thenReturn(null);
    Mockito.when(stockRepository.save(stockToBeCreated)).thenReturn(stockToBeCreated);
    //when
    Stock createdStock = stockService.createNewStock(stockToBeCreated);
    //then
    Assert.assertSame(stockToBeCreated,createdStock);
    Mockito.verify(stockRepository,Mockito.times(1)).findByNameIgnoreCase("APPLE");
    Mockito.verify(stockRepository,Mockito.times(1)).save(stockToBeCreated);
  }

  @Test(expected = StockNotFoundException.class)
  public void whenUpdateNonExistingStock_thenGetStockNotFoundException(){
    stockService.putUpdateStock(null,mockStock);
  }

  @Test(expected = StockMissingInformationException.class)
  public void whenUpdateStockUsingMissingInformation_thenGetStockMissingInformationException(){
    Money money              = Money.parse("USD 23.47");
    Stock existingStock      = new Stock("APPLE",money);
    Optional<Stock> optional = Optional.of(existingStock);
    Mockito.when(stockRepository.findById((long)123)).thenReturn(optional);
    Mockito.when(mockStock.getName()).thenReturn(null);
    stockService.putUpdateStock((long)123,mockStock);
  }

  @Test
  public void whenUpdateStock_thenUpdateStock(){
    //given
    Money existingmoney      = Money.parse("USD 23.47");
    Stock existingStock      = new Stock("APPLE",existingmoney);
    Optional<Stock> optional = Optional.of(existingStock);
    Money updatedMoney       = Money.parse("USD 500.40");
    Stock updatedStock       = new Stock("APPLE",updatedMoney);
    Mockito.when(stockRepository.findById((long)123)).thenReturn(optional);
    Mockito.when(stockRepository.save(existingStock)).thenReturn(existingStock);
    //when
    Stock stockAfterUpdate = stockService.putUpdateStock((long)123,updatedStock);
    //then
    Assert.assertEquals(updatedStock.getMoney().getAmount(),stockAfterUpdate.getMoney().getAmount());
  }

}
