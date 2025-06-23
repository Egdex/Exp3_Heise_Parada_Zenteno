package com.projecto_2.projecto_etapa2_beta.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = "com.projecto_2.projecto_etapa2_beta.repositoryBD2",
  entityManagerFactoryRef = "entityManagerFactory2",
  transactionManagerRef = "transactionManager2"
)
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.bd2.datasource")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource2())
                .packages("com.projecto_2.projecto_etapa2_beta.entitiesBD2")
                .persistenceUnit("bd2")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager2(
            @Qualifier("entityManagerFactory2") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
