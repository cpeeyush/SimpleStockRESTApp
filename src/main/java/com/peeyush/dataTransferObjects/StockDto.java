package com.peeyush.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.peeyush.models.Stock;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.util.Assert;

@JsonInclude(Include.NON_NULL)
public class StockDto implements Serializable{

  private static final long serialVersionUID = -4381065734779014094L;
  
  private Long id;
  private String name;
  private String currentPrice;
  private LocalDateTime lastUpdate;

  public StockDto(){}

  public StockDto(Stock stock){
      Assert.notNull(stock ,"Stock should not be null");
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
