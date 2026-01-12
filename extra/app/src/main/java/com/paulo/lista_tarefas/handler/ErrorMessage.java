package com.paulo.lista_tarefas.handler;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorMessage {

    public static final String CODE = "CODE 1001";

    private String code;
    private String erro_message;
    private int status_code;
    private LocalDateTime localDateTime;

}
