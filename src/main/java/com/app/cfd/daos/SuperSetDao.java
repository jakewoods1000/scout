package com.app.cfd.daos;

import com.app.cfd.models.OrderedId;
import com.app.cfd.models.SuperSet;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface SuperSetDao extends Transactional<SuperSetDao> {
    @SqlUpdate("insert into super_sets (user_id, name, description, reps) values (:userId, :name, :description, :reps)")
    @GetGeneratedKeys
    UUID insert(@BindBean SuperSet superSet);

    @SqlUpdate("insert into sets_super_sets_join (set_id, set_order, super_set_id) values (:set_id::uuid, :set_order, :super_set_id::uuid)")
    void insertSetSuperSetLink(@Bind("set_id") UUID setId,
                               @Bind("set_order") Integer setOrder,
                               @Bind("super_set_id") UUID superSetId);

    @SqlUpdate("update super_sets SET name = :name, description = :description, reps = :reps WHERE id = :id")
    @GetGeneratedKeys
    UUID update(@BindBean SuperSet superSet);

    @SqlQuery("select * from super_sets where id= :id")
    @RegisterBeanMapper(SuperSet.class)
    SuperSet findById(@Bind("id") UUID id);

    @SqlQuery("select * from super_sets")
    @RegisterBeanMapper(SuperSet.class)
    List<SuperSet> getAllSuperSets();

    @SqlQuery("select set_id from sets_super_sets_join where super_set_id= :superSetId")
    List<UUID> getSetIdsBySuperSetId(@Bind("superSetId") UUID superSetId);

    @SqlQuery("select set_order as order, set_id as id from sets_super_sets_join where super_set_id= :super_set_id")
    @RegisterBeanMapper(OrderedId.class)
    List<OrderedId> getOrderedSetIdsBySuperSetId(@Bind("super_set_id") UUID superSetId);

    @SqlUpdate("delete from sets_super_sets_join where set_id = :set_id and super_set_id = :super_set_id")
    void deleteSetSuperSetLink(@Bind("set_id") UUID setId,
                               @Bind("super_set_id") UUID superSetId);

    @SqlUpdate("delete from super_sets where id = :super_set_id")
    void deleteSuperSet(@Bind("super_set_id") UUID id);

    @SqlUpdate("delete from super_sets_workouts_join where super_set_id = :super_set_id")
    void deleteSuperSetWorkoutLink(@Bind("super_set_id") UUID id);

}
