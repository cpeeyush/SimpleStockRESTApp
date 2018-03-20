package com.peeyush.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;
import org.springframework.util.Assert;

/**
 * This is Stock Entity Object.
 */
@Entity
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @Embedded
  private Money money;
  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime lastUpdate;

  protected Stock() {}

  public Stock(Long id,String name,Money money){
    Assert.notNull(id, "Id must not be null");
    Assert.notNull(name, "Name must not be null");
    Assert.notNull(money, "Money must not be null");
    this.id           = id;
    this.name         = StringUtils.trim(name);
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
}
