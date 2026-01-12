package com.paulo.lista_tarefas.controller;

import com.paulo.lista_tarefas.dto.TarefaRequestDto;
import com.paulo.lista_tarefas.entity.TarefaEntity;
import com.paulo.lista_tarefas.service.TarefaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api" )
@CrossOrigin( origins = "*" )
public class TarefaController {

    private TarefaServiceImpl tarefaService;

    public TarefaController(TarefaServiceImpl tarefa) {
        this.tarefaService = tarefa;
    }

    @GetMapping( "/tarefas" )
    public ResponseEntity<?> listarTarefas() {
        return ResponseEntity.ok(tarefaService.buscarTarefas());
    }

    @GetMapping( "/tarefa/{id}" )
    public ResponseEntity<?> listarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarTarefa(id));
    }

    @PostMapping( "/tarefa" )
    public ResponseEntity<?> criarTarefa(@RequestBody TarefaRequestDto tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
    }

    @PutMapping( "/tarefa/atualizar/{id}" )
    public ResponseEntity<?> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDto dto) {
            tarefaService.atualizarTarefa(id, dto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping( "/tarefa/deletar/{id}" )
    public ResponseEntity<?> deletarTarefa(@PathVariable Long id)  {
            tarefaService.apagarTarefa(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
