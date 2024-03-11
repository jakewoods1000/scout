package com.app.cfd.daos;

import com.app.cfd.models.Tag;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface TagDao extends Transactional<TagDao> {
    @SqlUpdate("insert into tags (name, description, type) values(:name, :description, :type)")
    @GetGeneratedKeys
    UUID insert(@Bind("user_id") String userId,
                @Bind("name") String tagName,
                @Bind("description") String description,
                @Bind("type") Tag.Type type);

    @SqlQuery("select * from tags where id= :id")
    @RegisterBeanMapper(Tag.class)
    Tag getTag(@Bind("id") UUID id);

    @SqlQuery("select * from tags order by name")
    @RegisterBeanMapper(Tag.class)
    List<Tag> getAllTags();

    @SqlUpdate("update tags set name = :name, description = :description, type = :type where id = :id")
    void updateTag(@Bind("id") UUID id,
                   @Bind("name") String tagName,
                   @Bind("type") Tag.Type type,
                   @Bind("description") String description);

    @SqlUpdate("delete from tags where id = :id")
    void deleteById(@Bind("id") UUID id);

    @SqlUpdate("delete from exercises_tags_join where tag_id = :id")
    void deleteJoinsFromExercisesByTagId(@Bind("id") UUID id);

    @SqlUpdate("delete from exercises_tags_join where exercise_id = :id")
    void deleteJoinsFromExercisesByExerciseId(@Bind("id") UUID id);

    @SqlUpdate("delete from sets_tags_join where tag_id = :id")
    void deleteJoinsFromSetsByTagId(@Bind("id") UUID id);

    @SqlUpdate("delete from sets_tags_join where set_id = :id")
    void deleteJoinsFromSetsBySetId(@Bind("id") UUID id);

    @SqlUpdate("delete from super_sets_tags_join where tag_id = :id")
    void deleteJoinsFromSuperSetsByTagId(@Bind("id") UUID id);

    @SqlUpdate("delete from super_sets_tags_join where super_set_id = :id")
    void deleteJoinsFromSuperSetsBySuperSetId(@Bind("id") UUID id);

    @SqlUpdate("delete from workouts_tags_join where tag_id = :id")
    void deleteJoinsFromWorkoutsByTagId(@Bind("id") UUID id);

    @SqlUpdate("delete from workouts_tags_join where workout_id = :id")
    void deleteJoinsFromWorkoutsByWorkoutId(@Bind("id") UUID id);

    @SqlUpdate("insert into exercises_tags_join (tag_id, exercise_id) values(:tagId, :exerciseId)")
    @GetGeneratedKeys
    UUID tagExercise(@Bind("tagId") UUID tagId,
                     @Bind("exerciseId") UUID exerciseId);

    @SqlUpdate("insert into super_sets_tags_join (tag_id, super_set_id) values(:tagId, :superSetId)")
    @GetGeneratedKeys
    UUID tagSuperSet(@Bind("tagId") UUID tagId,
                     @Bind("superSetId") UUID superSetId);

    @SqlUpdate("insert into sets_tags_join (tag_id, set_id) values(:tagId, :setId)")
    @GetGeneratedKeys
    UUID tagSet(@Bind("tagId") UUID tagId,
                @Bind("setId") UUID setId);

    @SqlUpdate("insert into workouts_tags_join (tag_id, workout_id) values(:tagId, :workoutId)")
    @GetGeneratedKeys
    UUID tagWorkout(@Bind("tagId") UUID tagId,
                    @Bind("workoutId") UUID workoutId);
}
