package com.app.cfd.daos;

import com.app.cfd.models.OrderedId;
import com.app.cfd.models.Workout;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface WorkoutDao extends Transactional<WorkoutDao> {
    @SqlUpdate("insert into workouts (name, description, user_id, admin_user) values (:name, :description, :userId, :adminUser)")
    @GetGeneratedKeys
    UUID insert(@BindBean Workout workout);

    @SqlUpdate("insert into super_sets_workouts_join (super_set_id, super_set_order, workout_id) values (:superSetId, :superSetOrder, :workoutId)")
    void insertSuperSetWorkoutLink(@Bind("superSetId") UUID superSetId,
                                   @Bind("superSetOrder") Integer superSetOrder,
                                   @Bind("workoutId") UUID workoutId);

    @SqlQuery("select * from workouts where id = :id")
    @RegisterBeanMapper(Workout.class)
    Workout findById(@Bind("id") UUID id);

    @SqlQuery("select * from workouts")
    @RegisterBeanMapper(Workout.class)
    List<Workout> getAll();

    @SqlQuery("select super_set_order as order, super_set_id as id from super_sets_workouts_join where workout_id = :workoutId")
    @RegisterBeanMapper(OrderedId.class)
    List<OrderedId> getSuperSetIdsByWorkoutId(@Bind("workoutId") UUID workoutId);

    @SqlUpdate("delete from workouts where id = :workoutId")
    void deleteWorkout(@Bind("workoutId") UUID id);

    @SqlUpdate("delete from super_sets_workouts_join where workout_id = :workoutId and super_set_id = :superSetId")
    void deleteSuperSetWorkoutLink(@Bind("superSetId") UUID superSetId,
                                   @Bind("workoutId") UUID workoutId);

    @SqlUpdate("delete from super_sets_workouts_join where workout_id = :workoutId")
    void deleteAllSuperSetWorkoutLinks(@Bind("workoutId") UUID workoutId);

    @SqlUpdate("update workouts SET name = :name, description = :description WHERE id = :id")
    @GetGeneratedKeys
    UUID update(@Bind("id") UUID workoutId,
                @Bind("name") String workoutName,
                @Bind("description") String description);
}
