package com.peeyush.requests;


import com.peeyush.validators.ValidJodaMoney;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Object which Represents the Create A New Stock Request Body.
 */
public class CreateNewStockRequest {

  @NotNull(message = "Name should not be null")
  @Size(min = 2 , message = "Name should have at least 2 characters")
  private String name;
  @NotNull(message = "Current Price should not be null. Use a valid price e.g. USD 23.56")
  @ValidJodaMoney
  private String currentPrice;

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

}
