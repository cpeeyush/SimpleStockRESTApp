package com.peeyush.model;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.joda.money.Money;

public class Stock {

  @Min(1)
  private Long id;
  @NotNull
  private String name;
  @NotNull
  private Money price;
  @NotNull
  private LocalDateTime lastUpdate;
  private String description;

  public Stock(Long id,String name,Money price,LocalDateTime lastUpdate){
    this.id         = id;
    this.name       = name;
    this.price      = price;
    this.lastUpdate = lastUpdate;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
