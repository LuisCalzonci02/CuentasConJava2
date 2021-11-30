package com.NttData.CuentasSevice.config;


import com.NttData.CuentasSevice.config.exception.NoCuentasException;
import com.NttData.CuentasSevice.config.exception.NoLoginException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SistemaConfiguration extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ NoLoginException.class })
    protected ResponseEntity<Object> noLogin(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Debe de iniciar sesión.",
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ NoCuentasException.class })
    protected ResponseEntity<Object> nullCuentas(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Aún no tiene cuentas asociadas.",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
