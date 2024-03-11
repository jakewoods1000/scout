package com.app.cfd.models.users;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class User {
    private String id;
    private String email;
    private boolean emailVerified;
    private boolean test;

    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
    @Nullable
    private LocalDateTime deactivatedAt;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", emailVerified='" + emailVerified + '\'' +
                ", test=" + test +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deactivatedAt=" + deactivatedAt +
                '}';
    }
}