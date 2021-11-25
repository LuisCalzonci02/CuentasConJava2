package com.NttData.CuentasSevice.exception;

import com.NttData.CuentasSevice.controller.CuentaInxistenteEception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class exeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CuentaInxistenteEception.class})
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request
    ){
        return handleExceptionInternal(ex," Cuenta no encontrada",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
