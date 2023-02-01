package com.app.cfd.models.quantity;

import com.app.cfd.enums.QuantityTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Reps.class, name = "REPS"),
        @JsonSubTypes.Type(value = Timed.class, name = "TIMED"),
        @JsonSubTypes.Type(value = Other.class, name = "OTHER")
})
@Data
public class Quantity<T> {
    protected  T quantity;
}
