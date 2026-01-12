package com.paulo.lista_tarefas.service;

import com.paulo.lista_tarefas.dto.TarefaRequestDto;
import com.paulo.lista_tarefas.dto.TarefaResponseDto;
import com.paulo.lista_tarefas.entity.TarefaEntity;
import com.paulo.lista_tarefas.exception.TarefaNaoEncontradaException;
import com.paulo.lista_tarefas.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TarefaServiceImpl implements TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaServiceImpl(TarefaRepository r) {
        this.tarefaRepository = r;
    }

    @Override
    public TarefaResponseDto salvarTarefa(TarefaRequestDto dto) {
        TarefaEntity tarefa = tarefaRepository.save(transformaEmTarefa(dto));
        return transformaEmDto(tarefa);
    }

    @Override
    public TarefaResponseDto buscarTarefa(Long id) {
        TarefaEntity tarefa = tarefaRepository.findById(id)
                .orElseThrow(
                        () -> new TarefaNaoEncontradaException("Não existe tarefa com esse ID.")
                );
        return transformaEmDto(tarefa);
    }

    @Override
    public List<TarefaResponseDto> buscarTarefas() {
        List<TarefaResponseDto> listaTarefasDto = new ArrayList<>();
        List<TarefaEntity> listaTarefas = tarefaRepository.findAll();
        for(TarefaEntity tarefa : listaTarefas) {
            TarefaResponseDto dto = new TarefaResponseDto(
                    tarefa.getId(),
                    tarefa.getNome(),
                    tarefa.getPrioridade(),
                    tarefa.getRealizado()
            );
            listaTarefasDto.add(dto);
        }
        return listaTarefasDto;
    }

    @Override
    public void atualizarTarefa(Long id, TarefaRequestDto dto) {
        tarefaRepository.findById(id)
                .ifPresentOrElse(
                        tarefa -> {
                            if (dto.getNome() != null) {
                                tarefa.setNome(dto.getNome());
                            }
                            if (dto.getPrioridade() != null) {
                                tarefa.setPrioridade(dto.getPrioridade());
                            }
                            if (dto.getRealizado() != null) {
                                tarefa.setPrioridade(dto.getPrioridade());
                            }
                            tarefaRepository.save(tarefa);
                        },
                        () -> {
                            throw  new TarefaNaoEncontradaException("Não existe tarefa com esse ID.");
                        }
                );
    }

    @Override
    public void apagarTarefa(Long id) {
         tarefaRepository.findById(id)
                 .ifPresentOrElse(
                         tarefa ->  tarefaRepository.delete(tarefa),
                         () -> {
                             throw new TarefaNaoEncontradaException("Não existe tarefa com esse ID.");
                         }
                 );
    }

    @Override
    public TarefaEntity transformaEmTarefa(TarefaRequestDto dto) {
        TarefaEntity tarefa = TarefaEntity.builder()
                .nome(dto.getNome())
                .prioridade(dto.getPrioridade())
                .realizado(dto.getRealizado())
                .build();
        return tarefa;
    }

    @Override
    public TarefaResponseDto transformaEmDto(TarefaEntity tarefa) {
        TarefaResponseDto dto = new TarefaResponseDto(
                tarefa.getId(),
                tarefa.getNome(),
                tarefa.getPrioridade(),
                tarefa.getRealizado()
        );
        return dto;
    }

}
