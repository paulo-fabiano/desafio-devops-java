package com.paulo.lista_tarefas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "tarefas" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaEntity {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;
    private String nome;
    private Integer prioridade;
    private Boolean realizado;

}
