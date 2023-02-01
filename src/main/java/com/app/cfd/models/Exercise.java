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
    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private LocalDateTime updated_at;
}
