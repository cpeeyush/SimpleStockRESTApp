package com.peeyush.models;

import java.time.LocalDateTime;
import org.apache.commons.lang.StringUtils;
import org.joda.money.Money;
import org.springframework.util.Assert;

/**
 * This is Stock Entity Object.
 * Add @Entity to this class in case of JPA.
 */
public class Stock {

  private Long id;
  private String name;
  private Money money;
  private LocalDateTime lastUpdate;

  public Stock(Long id,String name,Money money,LocalDateTime lastUpdate){
    Assert.notNull(id, "Id must not be null");
    Assert.notNull(name, "Name must not be null");
    Assert.notNull(money, "Money must not be null");
    Assert.notNull(lastUpdate, "lastUpdate must not be null");
    this.id           = id;
    this.name         = StringUtils.trim(name);
    this.money        = money;
    this.lastUpdate   = lastUpdate;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public Money getMoney() {
    return money;
  }

  public LocalDateTime getLastUpdate() {

    return lastUpdate;
  }

}
