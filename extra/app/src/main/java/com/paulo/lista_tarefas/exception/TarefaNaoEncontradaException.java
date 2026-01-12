package com.paulo.lista_tarefas.exception;

public class TarefaNaoEncontradaException extends RuntimeException{

    public TarefaNaoEncontradaException(String erro) {
        super(erro);
    }


}
