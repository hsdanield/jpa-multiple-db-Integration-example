package com.jumperdb.domain.postgresql.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "usuario")
@ToString
public class UsuarioPostgresql {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

}
