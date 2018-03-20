package com.peeyush.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Very simple controller for the root path (/)
 */
@RestController
public class MainController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<String> rootResponse() {
    String welcomeMessage = "Welcome to the Simple Stock App.See the api docs for correct paths.";
    return new ResponseEntity<>(welcomeMessage, HttpStatus.OK);
  }

}
