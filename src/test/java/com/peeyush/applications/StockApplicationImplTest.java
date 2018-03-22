package com.peeyush.applications;



import com.peeyush.dataTransferObjects.StockDto;
import com.peeyush.models.Stock;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.service.StockService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StockApplicationImplTest {

    @TestConfiguration
    static class StockApplicationImplTestContextConfiguration {
      @Bean
      public StockApplication stockApplication(){
        return new StockApplicationImpl();
      }

    }
    @Autowired
    private StockApplication stockApplication;
    @MockBean
    private StockService stockService;
    @MockBean
    private CreateNewStockRequest createNewStockRequest;
    @MockBean
    private UpdateStockRequest updateStockRequest;
    @MockBean
    private HttpServletRequest request;

    @Test
    public void whenGetAllStocksResponse_thenReturnAllStockResponseEntity(){
      //given
      Money money            = Money.parse("USD 23.47");
      Stock stock1           = new Stock("APPLE",money);
      Stock stock2           = new Stock("GOOGLE",money);
      List<Stock>  stockList = new ArrayList<>();
      stockList.add(stock1);
      stockList.add(stock2);
      Mockito.when(stockService.getAllStocks()).thenReturn(stockList);
      //when
      ResponseEntity<List<StockDto>> responseEntity = stockApplication.getAllStocksResponse();
      //then
      Assert.assertEquals(stockList.size(),responseEntity.getBody().size());
      Assert.assertEquals(stockList.get(0).getName(),responseEntity.getBody().get(0).getName());
      Assert.assertEquals(stockList.get(1).getName(),responseEntity.getBody().get(1).getName());
      Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
      Mockito.verify(stockService,Mockito.times(1)).getAllStocks();
    }

    @Test
    public void whenGetSingleStockResponse_thenReturnSingleStockResponseEntity(){
      //given
      Money money            = Money.parse("USD 23.47");
      Stock stock1           = new Stock("APPLE",money);
      Mockito.when(stockService.getSingleStock(MockitoHamcrest.longThat(Matchers.any(Long.class)))).thenReturn(stock1);
      //when
      ResponseEntity<StockDto> responseEntity = stockApplication.getSingleStockResponse((long)122);
      //then
      Assert.assertEquals(stock1.getName(),responseEntity.getBody().getName());
      Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
      Mockito.verify(stockService,Mockito.times(1)).getSingleStock((long)122);
    }

    @Test
    public void whenCreateNewStock_thenReturnNewlyCreatedStock(){
      //given
      Money money    = Money.parse("USD 23.47");
      Stock newStock = new Stock("APPLE",money);
      Mockito.when(createNewStockRequest.getCurrentPrice()).thenReturn("USD 23.47");
      Mockito.when(createNewStockRequest.getName()).thenReturn("APPLE");
      Mockito.when(stockService.createNewStock(MockitoHamcrest.argThat(Matchers.any(Stock.class)))).thenReturn(newStock);
      Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost"));
      //when
      ResponseEntity<StockDto> responseEntity = stockApplication.createNewStock(createNewStockRequest,request);
      //then
      Assert.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
      Assert.assertEquals("APPLE",responseEntity.getBody().getName());
      Assert.assertEquals("USD 23.47",responseEntity.getBody().getCurrentPrice());
      Assert.assertEquals("localhost",responseEntity.getHeaders().getLocation().getHost());
      Mockito.verify(createNewStockRequest,Mockito.times(1)).getCurrentPrice();
      Mockito.verify(createNewStockRequest,Mockito.times(1)).getName();
      Mockito.verify(request,Mockito.times(1)).getRequestURL();
    }

    @Test
    public void whenPutUpdateStock_thenReturnUpdatedStock(){
      //given
      Money money        = Money.parse("USD 23.47");
      Stock stock        = new Stock("APPLE",money);
      Money updatedMoney = Money.parse("USD 29.30");
      Stock updatedStock = new Stock("APPLE",updatedMoney);
      Mockito.when(stockService.getSingleStock(MockitoHamcrest.longThat(Matchers.any(Long.class)))).thenReturn(stock);
      Mockito.when(updateStockRequest.getCurrentPrice()).thenReturn("USD 29.30");
      Mockito.when(stockService.putUpdateStock(MockitoHamcrest.longThat(Matchers.any(Long.class)),
          MockitoHamcrest.argThat(Matchers.any(Stock.class)))).thenReturn(updatedStock);
      //when
      ResponseEntity<StockDto> responseEntity = stockApplication.putUpdateStock((long)122,updateStockRequest);
      //then
      Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
      Assert.assertEquals("USD 29.30",responseEntity.getBody().getCurrentPrice());
      Assert.assertEquals("APPLE",responseEntity.getBody().getName());
      Mockito.verify(updateStockRequest,Mockito.times(1)).getCurrentPrice();
    }
}
