package com.peeyush.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Class Represents when a Stock Entity Doesn't have all the required(stock Id,Name & Price) fields.
 * It extends the Runtime Exception as we want it of Unchecked type because client cannot do more with this exception.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "You must include stock Name & Current Price")
public class StockMissingInformationException extends RuntimeException {

  public StockMissingInformationException(final String message) {

    super(message);
  }

  public StockMissingInformationException(final String message, final Throwable t) {

    super(message, t);
  }

  public StockMissingInformationException(final Throwable t) {

    super(t);
  }
}
