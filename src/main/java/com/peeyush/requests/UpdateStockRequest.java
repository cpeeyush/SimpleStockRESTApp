package com.peeyush.requests;

import com.peeyush.validators.ValidJodaMoney;
import javax.validation.constraints.NotNull;

/**
 * Request Object which Represents the Update Stock Request Body.
 */
public class UpdateStockRequest {

  @NotNull(message = "Current Price should not be null. Use a valid price e.g. USD 23.56")
  @ValidJodaMoney
  private String currentPrice;

  public String getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(String currentPrice) {
    this.currentPrice = currentPrice;
  }
}
