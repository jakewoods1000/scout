package com.app.cfd.models.users;


import com.app.cfd.TimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserValidation implements Serializable {
    private String token;
    private Timestamp tokenIssue;

    private String passwordResetToken;

    private Timestamp passwordResetIssue;

    private Timestamp createdAt;

    private Long version;
    private UUID userId;

    public UserValidation(User userId) {
        this.userId = userId.getId();
    }

    public void newToken() {
        setToken(UUID.randomUUID().toString());
        setTokenIssue(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    }

    public boolean tokenIsCurrent() {
        TimeUtils time = new TimeUtils();
        return Math.abs(getTokenIssue().getTime() - time.now().getTime()) < 1000 * 60 * 60 * 24;
    }

    public boolean passwordValidationIsCurrent() {
        TimeUtils time = new TimeUtils();
        return Math.abs(getPasswordResetIssue().getTime() - time.now().getTime()) < 1000 * 60 * 5;
    }

    public void newPasswordResetToken() {
        setPasswordResetToken(UUID.randomUUID().toString());
        setPasswordResetIssue(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    }

}