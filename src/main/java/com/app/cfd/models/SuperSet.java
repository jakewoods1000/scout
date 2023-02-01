package com.app.cfd.models;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SuperSet {
    private UUID id;
    private String name;
    @Nullable
    private String description;
    private List<UUID> setIds;
    private Integer reps;
    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private LocalDateTime updated_at;
}
