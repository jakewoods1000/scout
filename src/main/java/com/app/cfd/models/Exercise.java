package com.app.cfd.models;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Exercise {
    private UUID id;
    private String name;
    @Nullable
    private String description;
    private String userId;
    private boolean adminUser;
    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
}
