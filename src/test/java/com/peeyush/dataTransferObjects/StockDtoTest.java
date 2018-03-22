package com.peeyush.dataTransferObjects;

import com.peeyush.models.Stock;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;



public class StockDtoTest {

    @Test
    public void verifyConstructionOfDtoWhenStockObjectGiven(){
      //given
      Money money    = Money.parse("USD 23.47");
      Stock stock    = new Stock("APPLE",money);
      Stock stockSpy = Mockito.spy(stock);
      Mockito.when(stockSpy.getId()).thenReturn((long)122);
      //when
      StockDto stockDto = new StockDto(stockSpy);
      //then
      Assert.assertEquals(stockSpy.getName(),stockDto.getName());
      Assert.assertEquals(122,stockDto.getId().longValue());
      Assert.assertEquals("USD 23.47",stockDto.getCurrentPrice());
    }

}
