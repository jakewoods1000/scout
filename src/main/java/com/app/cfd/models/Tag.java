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
    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private LocalDateTime updated_at;

    public enum Type {MUSCLE_GROUP, STYLE, OTHER}
}
