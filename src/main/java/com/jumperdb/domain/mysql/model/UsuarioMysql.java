package com.jumperdb.domain.mysql.model;

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
@ToString
@Table(name = "usuario")
public class UsuarioMysql {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

}
