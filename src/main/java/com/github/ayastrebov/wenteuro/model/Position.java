package com.github.ayastrebov.wenteuro.model;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class Position {

    String id;
    String name;
    String type;

    BigDecimal latitude;
    BigDecimal longitude;
}
