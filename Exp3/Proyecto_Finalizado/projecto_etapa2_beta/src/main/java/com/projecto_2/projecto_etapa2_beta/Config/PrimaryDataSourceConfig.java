package com.projecto_2.projecto_etapa2_beta.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;


//--- Codiogo para la configuración de la base de datos primaria (bd1) ---

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.projecto_2.projecto_etapa2_beta.repositoryBD1",
    entityManagerFactoryRef = "entityManagerFactory1",
    transactionManagerRef = "transactionManager1"
)
public class PrimaryDataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource1())
                .packages("com.projecto_2.projecto_etapa2_beta.entitiesBD1")
                .persistenceUnit("bd1")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager1(
            @Qualifier("entityManagerFactory1") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
