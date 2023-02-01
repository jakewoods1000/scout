package com.app.cfd.daos;

import com.app.cfd.models.SuperSet;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface SuperSetDao extends Transactional<SuperSetDao> {
    @SqlUpdate("insert into super_sets (name, description, reps) values (:name, :description, :reps)")
    @GetGeneratedKeys
    UUID insert(@Bind("name") String superSetName,
                @Bind("description") String description,
                @Bind("reps") Integer reps);

    @SqlUpdate("insert into sets_super_sets_join (set_id, super_set_id) values (:setId, :superSetId)")
    void insertSetSuperSetLink(@Bind("setId") UUID setId,
                               @Bind("superSetId") UUID superSetId);

    @SqlUpdate("update super_sets SET name = :name, description = :description, reps = :reps WHERE id = :id")
    @GetGeneratedKeys
    UUID update(@Bind("id") UUID superSetId,
                @Bind("name") String superSetName,
                @Bind("description") String description,
                @Bind("reps") Integer reps);
    @SqlQuery("select * from super_sets where id= :id")
    @RegisterBeanMapper(SuperSet.class)
    SuperSet getSuperSet(@Bind("id") UUID id);

    @SqlQuery("select set_id from sets_super_sets_join where super_set_id= :superSetId")
    List<UUID> getSetIdsBySuperSetId(@Bind("superSetId") UUID superSetId);

    @SqlUpdate("delete from sets_super_sets_join where set_id = :setId and super_set_id = :superSetId")
    void deleteSetSuperSetLink(@Bind("setId") UUID setId,
                               @Bind("superSetId") UUID superSetId);
    @SqlUpdate("delete from super_sets where id = :superSetId")
    void deleteSuperSet(@Bind("superSetId") UUID id);

    @SqlUpdate("delete from super_sets_workouts_join where super_set_id = :superSetId")
    void deleteSuperSetWorkoutLink(@Bind("superSetId") UUID id);

}
