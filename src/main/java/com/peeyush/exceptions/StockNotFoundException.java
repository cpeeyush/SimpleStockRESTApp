package com.peeyush.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Class Represents when a Stock Not Found in the system.
 * It extends the Runtime Exception as we want it of Unchecked type because client cannot do more with this exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Stock Not Found")
public class StockNotFoundException extends RuntimeException {

  public StockNotFoundException(final String message) {

    super(message);
  }

  public StockNotFoundException(final String message, final Throwable t) {

    super(message, t);
  }

  public StockNotFoundException(final Throwable t) {

    super(t);
  }
}
