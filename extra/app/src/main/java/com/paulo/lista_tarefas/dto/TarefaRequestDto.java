package com.paulo.lista_tarefas.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TarefaRequestDto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private String nome;
    private Integer prioridade;
    private Boolean realizado;

}
