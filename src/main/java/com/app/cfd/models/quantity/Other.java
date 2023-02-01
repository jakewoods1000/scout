package com.app.cfd.models.quantity;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("OTHER")
public class Other extends Quantity<String> {
    public Other(){
        super();
    }
}
