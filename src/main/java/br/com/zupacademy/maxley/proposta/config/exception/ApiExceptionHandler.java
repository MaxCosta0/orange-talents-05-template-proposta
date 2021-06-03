package br.com.zupacademy.maxley.proposta.config.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroPadrao> methodArgumentNotValidHandler(MethodArgumentNotValidException exception){
        List<ErroPadrao> errosPadrao = new ArrayList<>();
        List<FieldError> errosCapturados = exception.getBindingResult().getFieldErrors();

        for(FieldError erroCapturado: errosCapturados){
            String mensagem = messageSource.getMessage(erroCapturado, LocaleContextHolder.getLocale());
            ErroPadrao erroPadrao = new ErroPadrao(erroCapturado.getField(), mensagem);
            errosPadrao.add(erroPadrao);
        }

        return errosPadrao;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroPadrao> ResponseStatusExceptionHandler(ResponseStatusException exception){
        ErroPadrao erro = new ErroPadrao(exception.getReason());
        return ResponseEntity.status(exception.getStatus()).body(erro);
    }
}
