package com.jumperdb.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jumperdb.domain.mysql.repository"},
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef= "mysqlTransactionManager"
)
public class MysqlDataSourceConfig {
    @Autowired
    private Environment environment;

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().driverClassName(environment.getProperty("spring.datasource-mysql.driverClassName")).build();

//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource-mysql.jdbcUrl"));
//        dataSource.setUsername(env.getProperty("spring.datasource-mysql.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource-mysql.password"));
//        dataSource.setDriverClassName(env.getProperty("spring.datasource-mysql.driverClassName"));
//        return dataSource;

    }

    @Primary
    @Bean(name = "mysqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("mysqlDataSource") DataSource dataSource) {

        /*
        *final HashMap<String, Object> properties = new HashMap<String, Object>();
        *properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.datasource-postgresql.custom-param.hibernate.ddl-auto"));
        */


        return builder
                .dataSource(dataSource)
//                .properties()
                .packages("com.jumperdb.domain.mysql.model")
                .persistenceUnit("mysql-persist")
                .build();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
