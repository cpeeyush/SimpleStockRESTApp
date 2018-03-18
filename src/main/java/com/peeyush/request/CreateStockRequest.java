package com.peeyush.request;

import javax.validation.constraints.NotNull;

/**
 * Request Object which Represents the Create Stock Request Body.
 */
public class CreateStockRequest {

  @NotNull
  private String name;
  @NotNull
  private String price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
