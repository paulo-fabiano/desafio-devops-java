package com.paulo.lista_tarefas.service;

import com.paulo.lista_tarefas.dto.TarefaRequestDto;
import com.paulo.lista_tarefas.dto.TarefaResponseDto;
import com.paulo.lista_tarefas.entity.TarefaEntity;
import com.paulo.lista_tarefas.exception.TarefaNaoEncontradaException;

import java.util.List;

public interface TarefaService {

    TarefaResponseDto salvarTarefa(TarefaRequestDto dto);
    TarefaResponseDto buscarTarefa(Long id) throws TarefaNaoEncontradaException;
    List<TarefaResponseDto> buscarTarefas();
    void atualizarTarefa(Long id, TarefaRequestDto tarefa) throws TarefaNaoEncontradaException;
    void apagarTarefa(Long id) throws TarefaNaoEncontradaException;
    TarefaEntity transformaEmTarefa(TarefaRequestDto tarefa);
    TarefaResponseDto transformaEmDto(TarefaEntity tarefa);

}
