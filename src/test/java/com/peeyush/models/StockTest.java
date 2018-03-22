package com.peeyush.models;

import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

public class StockTest {

  @Test(expected = IllegalArgumentException.class)
  public void whenGiveNullName_thenGetIllegalArgumentException(){
    Money money = Money.parse("USD 23.47");
    Stock stock = new Stock(null,money);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenGiveNullMoney_thenGetIllegalArgumentException(){
    Stock stock = new Stock("APPLE",null);
  }

  @Test
  public void verifyStockNameShouldBeTrimmedAndUpperCase(){
    Money money = Money.parse("USD 23.47");
    Stock stock = new Stock("   AppLE   ",money);
    Assert.assertEquals("APPLE",stock.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSetNullMoney_thenGetIllegalArgumentException(){
    Money money = Money.parse("USD 23.47");
    Stock stock = new Stock("APPLE",money);
    stock.setMoney(null);
  }


}
