package com.paulo.lista_tarefas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TarefaResponseDto {

    @JsonProperty( "id" )
    private Long id;
    @JsonProperty( "nome" )
    private String nome;
    @JsonProperty( "prioridade" )
    private Integer prioridade;
    @JsonProperty( "realizado" )
    private Boolean realizado;

}
