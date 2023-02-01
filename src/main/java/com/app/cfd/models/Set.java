package com.app.cfd.models;

import com.app.cfd.models.quantity.Quantity;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Set {
    private UUID id;
    private String name;
    @Nullable
    private String description;
    private UUID exercise_id;
    private String style;
    private Quantity quantity;
    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private LocalDateTime updated_at;
}
