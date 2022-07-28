package com.jumperdb.domain.mysql.repository;

import com.jumperdb.domain.mysql.model.UsuarioMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMysqlRepository extends JpaRepository<UsuarioMysql, Long> {
}
