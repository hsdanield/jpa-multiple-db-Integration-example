package com.jumperdb;

import com.jumperdb.domain.mysql.model.UsuarioMysql;
import com.jumperdb.domain.mysql.repository.UsuarioMysqlRepository;
import com.jumperdb.domain.postgresql.model.UsuarioPostgresql;
import com.jumperdb.domain.postgresql.repository.UsuarioPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JumperdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumperdbApplication.class, args);
	}

	@Autowired
	private UsuarioMysqlRepository usuarioMysqlRepository;

	@Autowired
	private UsuarioPostgresqlRepository usuarioPostgresqlRepository;

	@PostConstruct
	public void postConstruct() {
		UsuarioMysql usuarioMysql = new UsuarioMysql();
		usuarioMysql.setId(1L);
		usuarioMysql.setNome("Daniel MySQL");
		usuarioMysql = usuarioMysqlRepository.save(usuarioMysql);

		System.out.println(usuarioMysql);
		System.out.println(usuarioMysqlRepository.findById(usuarioMysql.getId()));

		UsuarioPostgresql usuarioPostgresql = new UsuarioPostgresql();
		usuarioPostgresql.setId(1L);
		usuarioPostgresql.setNome("Daniel Postgresql");
		usuarioPostgresql = usuarioPostgresqlRepository.save(usuarioPostgresql);

		System.out.println(usuarioPostgresql);
		System.out.println(usuarioMysqlRepository.findById(usuarioPostgresql.getId()));
	}

}
