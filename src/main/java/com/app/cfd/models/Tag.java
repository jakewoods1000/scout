package com.app.cfd.models;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Tag {
    private UUID id;
    private String name;
    @Nullable
    private String description;
    private Type type;
    private String userId;
    private boolean adminUser;
    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;

    public enum Type {MUSCLE_GROUP, STYLE, OTHER}
}
