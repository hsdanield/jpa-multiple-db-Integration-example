package com.jumperdb.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jumperdb.domain.postgresql.repository"},
        entityManagerFactoryRef = "postgresqlEntityManager",
        transactionManagerRef= "postgresqlTransactionManager"
)
public class PostgresqlDataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-postgresql")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().driverClassName(environment.getProperty("spring.datasource-postgresql.driverClassName")).build();
    }

    @Bean(name = "postgresqlEntityManager")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("postgresqlDataSource") DataSource dataSource) {
         /*
         *final HashMap<String, Object> properties = new HashMap<String, Object>();
         *properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.datasource-postgresql.custom-param.hibernate.ddl-auto"));
         */

        return builder
                .dataSource(dataSource)
//                .properties()
                .packages("com.jumperdb.domain.postgresql.model")
                .persistenceUnit("postgresql-persist")
                .build();

    }

    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManagerTransactionManager(@Qualifier("postgresqlEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
