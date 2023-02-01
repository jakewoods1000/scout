package com.app.cfd.controllers.model;

import com.app.cfd.enums.TaggableTypes;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TagInput {
    UUID entityToBeTagged;
    List<UUID> tags;
    TaggableTypes entityType;
}
