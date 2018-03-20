package com.peeyush.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is works with controllers and handle all possible type of exceptions and Generate meaningful error
 * response for the user.
 *
 * NOTICE: All Methods are self explanatory. No Documentation required.
 */
@ControllerAdvice
public class ExceptionHandlers {

  private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

  @ExceptionHandler(StockNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleStockNotFoundException(final StockNotFoundException ex) {
    log.warn("Stock not found thrown");
    return new ErrorResponse("STOCK_NOT_FOUND", ex.getMessage());
  }

  @ExceptionHandler(StockMissingInformationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ErrorResponse handleStockMissingInformationException(final StockMissingInformationException ex) {
    log.warn("Stock missing information thrown");
    return new ErrorResponse("STOCK_MISSING_INFORMATION", ex.getMessage());
  }

  @ExceptionHandler(StockAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse handleStockAlreadyExistsException(final StockAlreadyExistsException ex) {
    log.warn("Stock already exists thrown");
    return new ErrorResponse("STOCK_ALREADY_EXISTS", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleBadRequestException(MethodArgumentNotValidException exception) {

    String errorMsg = exception.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .findFirst()
        .orElse(exception.getMessage());

    return new ErrorResponse("BAD_REQUEST",errorMsg);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {

    StringBuilder errorMessage = new StringBuilder();

    for(ConstraintViolation violation : exception.getConstraintViolations()){
      errorMessage.append(violation.getPropertyPath().toString());
      errorMessage.append(":");
      errorMessage.append(violation.getMessage());
      errorMessage.append(System.lineSeparator());
    }

    return new ErrorResponse("BAD_REQUEST",errorMessage.toString());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ErrorResponse handleThrowable(final Throwable ex) {
    log.error("Unexpected Error", ex);
    return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occurred");
  }

}
