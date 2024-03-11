package com.app.cfd.daos;

import com.app.cfd.models.users.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.UUID;

public interface UserDao extends Transactional<UserDao>{
    @SqlUpdate("insert into users (id, email, email_verified) values (:id, :email, :email_verified)")
    @GetGeneratedKeys
    UUID insert(@Bind("id") String id,
                @Bind("email") String email,
                @Bind("email_verified") boolean emailVerified);

    @SqlQuery("select * from users where email = :email")
    @RegisterBeanMapper(User.class)
    User findByEmail(@Bind("email") String email);

    @SqlQuery("select * from users where id = :id")
    @RegisterBeanMapper(User.class)
    User findById(@Bind("id") String id);

    @SqlUpdate("update users SET email = :email, email_verified = :email_verified values where id = :id")
    void updateUser(@Bind("id") String id,
                    @Bind("email") String email,
                    @Bind("email_verified") boolean emailVerified);

    @SqlUpdate("update users SET deactivated_at = :now where id = :id")
    void deactivateUser(@Bind("id") UUID id);
}
