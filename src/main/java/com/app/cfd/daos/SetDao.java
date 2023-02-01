package com.app.cfd.daos;

import com.app.cfd.daos.utilities.set.QuantityMapper;
import com.app.cfd.models.Set;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;


public interface SetDao extends Transactional<SetDao> {
    @Transaction
    @SqlUpdate("insert into sets (name, description, quantity, exercise_id, style) values (:name, :description, :quantity::jsonb, :exercise_id, :style)")
    @GetGeneratedKeys
    UUID insert(@Bind("name") String setName,
                @Bind("description")String description,
                @Bind("quantity") String quantity,
                @Bind("exercise_id")UUID exerciseId,
                @Bind("style")String style);

    @SqlQuery("select * from sets where id= :id")
    @RegisterColumnMapper(QuantityMapper.class)
    @RegisterBeanMapper(Set.class)
    Set getSet(@Bind("id") UUID id);

    @SqlQuery("select * from sets order by name")
    @RegisterColumnMapper(QuantityMapper.class)
    @RegisterBeanMapper(Set.class)
    List<Set> getAllSets();

    @SqlUpdate("update sets set name = :name, description = :description, exercise_id = :exercise_id, style = :style, quantity = :quantity::jsonb where id = :id")
    void updateSet(@Bind("id") UUID id,
                   @Bind("name") String setName,
                   @Bind("description") String description,
                   @Bind("quantity") String quantity,
                   @Bind("exercise_id") UUID exercise_id,
                   @Bind("style") String style);

    @SqlUpdate("delete from sets where id = :id")
    void deleteById(@Bind("id") UUID id);
}
