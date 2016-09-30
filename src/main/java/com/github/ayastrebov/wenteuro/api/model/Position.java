package com.github.ayastrebov.wenteuro.api.model;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class Position {

    String _id;
    String name;
    String type;

    Geo geo_position;

    @Value
    public static class Geo {

        BigDecimal latitude;
        BigDecimal longitude;
    }
}
