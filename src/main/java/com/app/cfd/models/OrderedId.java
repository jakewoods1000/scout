package com.app.cfd.models;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderedId {
    private Integer order;
    private UUID id;
}
