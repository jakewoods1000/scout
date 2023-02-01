package com.app.cfd.models.quantity;

import com.app.cfd.enums.QuantityTypes;
import com.app.cfd.models.Time;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("TIMED")
public class Timed extends Quantity<Time> {
    public Timed(){
        super();
    }
}
