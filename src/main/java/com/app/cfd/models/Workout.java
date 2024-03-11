package com.app.cfd.models;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Workout {
    private UUID id;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private List<OrderedId> orderedSuperSetIds;
    private String userId;
    private boolean adminUser;
    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
}
