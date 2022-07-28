package com.jumperdb.domain.postgresql.repository;

import com.jumperdb.domain.postgresql.model.UsuarioPostgresql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPostgresqlRepository extends JpaRepository<UsuarioPostgresql, Long> {
}
