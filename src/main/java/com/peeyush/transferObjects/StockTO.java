package com.peeyush.transferObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.peeyush.models.Stock;
import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
public class StockTO {

  private Long id;
  private String name;
  private String currentPrice;
  private LocalDateTime lastUpdate;

  public StockTO(){}

  public StockTO(Stock stock){
      this.id           = stock.getId();
      this.name         = stock.getName();
      this.currentPrice = stock.getMoney().getCurrencyUnit() + " " +stock.getMoney().getAmount();
      this.lastUpdate   = stock.getLastUpdate();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(String currentPrice) {
    this.currentPrice = currentPrice;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
