package com.app.cfd.daos;

import com.app.cfd.models.Exercise;
import com.app.cfd.models.users.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserDao extends Transactional<UserDao> {
    @SqlUpdate("""
            insert into users 
            (username, password, version, token_validation, test) 
            values 
            (:username, :password, :version, :tokenValidation, :test)
            """)
    @GetGeneratedKeys
    UUID insert(@BindBean User user);
    @SqlQuery("select * from users where id = :id")
    @RegisterBeanMapper(User.class)
    User findById(@Bind("id") UUID id);

    @SqlUpdate("""
            delete from users where user = :userId 
            """)
    void delete(@Bind UUID userId);
    @SqlQuery("select * from users where username = :username")
    @RegisterBeanMapper(User.class)
    User findByUsername(@Bind("username") String username);

}
