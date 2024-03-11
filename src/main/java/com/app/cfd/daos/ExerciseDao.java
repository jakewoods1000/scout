package com.app.cfd.daos;

import com.app.cfd.models.Exercise;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;
public interface ExerciseDao extends Transactional<ExerciseDao> {
    @SqlUpdate("insert into exercises (user_id, name, description) values (:userId, :name, :description)")
    @GetGeneratedKeys
    UUID insert(@BindBean Exercise exercise);
    @SqlQuery("select * from exercises where id = :id")
    @RegisterBeanMapper(Exercise.class)
    Exercise findById(@Bind("id") UUID id);

    @SqlQuery("select * from exercises order by name")
    @RegisterBeanMapper(Exercise.class)
    List<Exercise> getAllExercises();

    @SqlUpdate("update exercises SET name = :name, description = :description where id = :id")
    void updateExercise(@BindBean Exercise exercise);

    @SqlUpdate("delete from exercises where id = :id")
    void deleteById(@Bind("id") UUID id);
}
