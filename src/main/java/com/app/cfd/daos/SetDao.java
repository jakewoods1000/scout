package com.app.cfd.daos;

import com.app.cfd.daos.utilities.set.QuantityMapper;
import com.app.cfd.models.Set;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.List;
import java.util.UUID;


public interface SetDao extends Transactional<SetDao> {
    //    TODO: Do we need to stringify the quantity and pass it in, or can we just pull it off the bound set?
    @Transaction
    @SqlUpdate("insert into sets (user_id, name, description, quantity, exercise_id, style) values (:userId, :name, :description, :quantity::jsonb, :exerciseId::uuid, :style)")
    @GetGeneratedKeys
    UUID insert(@BindBean Set set,
                @Bind("quantity") String quantity);

    @SqlQuery("select * from sets where id= :id")
    @RegisterColumnMapper(QuantityMapper.class)
    @RegisterBeanMapper(Set.class)
    Set findById(@Bind("id") UUID id);

    @SqlQuery("select * from sets order by name")
    @RegisterColumnMapper(QuantityMapper.class)
    @RegisterBeanMapper(Set.class)
    List<Set> getAllSets();

    @SqlUpdate("update sets set name = :name, description = :description, exercise_id = :exerciseId::uuid, style = :style, quantity = :quantity::jsonb where id = :id")
    void updateSet(@BindBean Set set,
                   @Bind("quantity") String quantity);

    @SqlUpdate("delete from sets where id = :id")
    void deleteById(@Bind("id") UUID id);
}
