package com.paulo.lista_tarefas.handler;

import com.paulo.lista_tarefas.exception.TarefaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<ErrorMessage> handleTarefaNaoEncontradaException(
            TarefaNaoEncontradaException tarefaNaoEncontradaException
    ) {
        ErrorMessage erro = ErrorMessage.builder()
                .code(ErrorMessage.CODE)
                .erro_message("Tarefa não encontrada.")
                .status_code(HttpStatus.NOT_FOUND.value())
                .localDateTime(LocalDateTime.now(ZoneId.of("America/Fortaleza")))
                .build();
       return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

}

