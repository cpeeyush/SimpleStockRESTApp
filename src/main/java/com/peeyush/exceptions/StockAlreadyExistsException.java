package com.peeyush.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Class Represents when a Stock already exists in the system.
 * It extends the Runtime Exception as we want it of Unchecked type because client cannot do more with this exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Stock Already Exists")
public class StockAlreadyExistsException extends RuntimeException {

  public StockAlreadyExistsException(final String message) {

    super(message);
  }

  public StockAlreadyExistsException(final String message, final Throwable t) {

    super(message, t);
  }

  public StockAlreadyExistsException(final Throwable t) {

    super(t);
  }

}
