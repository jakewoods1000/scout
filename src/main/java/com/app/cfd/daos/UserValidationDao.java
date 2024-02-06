package com.app.cfd.daos;

import com.app.cfd.models.users.User;
import com.app.cfd.models.users.UserValidation;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transactional;

import java.util.UUID;

public interface UserValidationDao extends Transactional<UserValidationDao> {
    @SqlUpdate("""
            insert into user_validations 
            (token, tokenIssue, password_reset_token, password_reset_issue, version, user_id) 
            values 
            (:token, :tokenIssue, :passwordResetToken, :passwordResetIssue, :version, :userId)
            """)
    @GetGeneratedKeys
    UUID insert(@BindBean UserValidation user);

    @SqlUpdate("""
            delete from user_validations where user_id = :userId 
            
            """)
    void deleteById(@Bind UUID userId);
    @SqlQuery("select * from user_validations where user_id = :id")
    @RegisterBeanMapper(UserValidation.class)
    UserValidation findById(@Bind("id") UUID userId);

    @SqlQuery("select * from user_validations where username = :username")
    @RegisterBeanMapper(UserValidation.class)
    UserValidation findByUsername(@Bind("username") String username);

    @SqlQuery("select * from user_validations where token = :token")
    @RegisterBeanMapper(UserValidation.class)
    UserValidation findByToken(@Bind String token);

}
