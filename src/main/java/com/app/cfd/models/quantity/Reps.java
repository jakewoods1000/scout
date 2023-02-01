package com.app.cfd.models.quantity;

import com.app.cfd.enums.QuantityTypes;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("REPS")
public class Reps extends Quantity<Integer> {
    public Reps(){
        super();
    }
}
