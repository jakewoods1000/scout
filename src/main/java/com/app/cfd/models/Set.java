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
    private UUID exerciseId;
    private String style;
    private Quantity quantity;
    private String userId;
    private boolean adminUser;
    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime updatedAt;
}
