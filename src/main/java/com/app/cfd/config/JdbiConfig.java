package com.app.cfd.config;

import com.app.cfd.daos.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource ds) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public ExerciseDao exerciseDao(Jdbi jdbi) {
        return jdbi.onDemand(ExerciseDao.class);
    }

    @Bean
    public SetDao setDao(Jdbi jdbi) {
        return jdbi.onDemand(SetDao.class);
    }

    @Bean
    public UserDao userDao(Jdbi jdbi) {
        return jdbi.onDemand(UserDao.class);
    }

    @Bean
    public TagDao tagDao(Jdbi jdbi) {
        return jdbi.onDemand(TagDao.class);
    }

    @Bean
    public SuperSetDao superSetDao(Jdbi jdbi) {
        return jdbi.onDemand(SuperSetDao.class);
    }

    @Bean
    public WorkoutDao workoutDao(Jdbi jdbi) {
        return jdbi.onDemand(WorkoutDao.class);
    }
}
