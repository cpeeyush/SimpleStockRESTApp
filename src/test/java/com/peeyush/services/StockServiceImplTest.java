package com.peeyush.services;


import com.peeyush.repositories.StockRepository;
import com.peeyush.service.StockService;
import com.peeyush.service.StockServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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

  @Test
  public void testIt(){
    Assert.assertTrue(true);
  }


}
