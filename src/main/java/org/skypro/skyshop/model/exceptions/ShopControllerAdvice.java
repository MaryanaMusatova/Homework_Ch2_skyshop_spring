package org.skypro.skyshop.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductError(NoSuchProductException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ShopError("404", ex.getMessage()));
    }

}