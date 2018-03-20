package com.peeyush.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;
import org.springframework.util.Assert;

/**
 * This is Stock Entity Object.
 */
@Entity
public class Stock {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;
  @Size(min = 2, max = 20)
  private String name;
  @Columns(columns = { @Column(name = "currency"), @Column(name = "amount") })
  @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency")
  private Money money;
  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime lastUpdate;

  protected Stock() {}

  public Stock(String name,Money money){
    Assert.notNull(name, "Name must not be null");
    Assert.notNull(money, "Money must not be null");
    this.name         = StringUtils.trim(name).toUpperCase();
    this.money        = money;
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

  public LocalDateTime getCreated() {

    return created;
  }

  public void setMoney(Money money) {
    Assert.notNull(money, "Money must not be null");
    this.money = money;
  }
}
