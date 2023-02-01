package com.app.cfd.daos;

import com.app.cfd.models.Workout;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface WorkoutDao extends Transactional<WorkoutDao> {
    @SqlUpdate("insert into workouts (name, description) values (:name, :description)")
    @GetGeneratedKeys
    UUID insert(@Bind("name") String workoutName,
                @Bind("description") String description);

    @SqlUpdate("insert into super_sets_workouts_join (super_set_id, workout_id) values (:superSetId, :workoutId)")
    void insertSuperSetWorkoutLink(@Bind("superSetId") UUID superSetId,
                                   @Bind("workoutId") UUID workoutId);

    @SqlQuery("select * from workouts where id = :id")
    Workout getWorkout(@Bind("id") UUID id);

    @SqlQuery("select super_set_id from super_sets_workouts_join where workout_id = :workoutId")
    List<UUID> getSuperSetIdsByWorkoutId(@Bind("workoutId") UUID workoutId);

    @SqlUpdate("delete from workouts where id = :workoutId")
    void deleteWorkout(@Bind("workoutId") UUID id);

    @SqlUpdate("delete from super_sets_workouts_join where workout_id = :workoutId and super_set_id = :superSetId")
    void deleteSuperSetWorkoutLink(@Bind("superSetId") UUID superSetId,
                                   @Bind("workoutId") UUID workoutId);

    @SqlUpdate("update workouts SET name = :name, description = :description WHERE id = :id")
    @GetGeneratedKeys
    UUID update(@Bind("id") UUID workoutId,
                @Bind("name") String workoutName,
                @Bind("description") String description);
}
