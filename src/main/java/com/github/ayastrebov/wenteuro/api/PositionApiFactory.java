package com.github.ayastrebov.wenteuro.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ayastrebov.wenteuro.model.Position;
import feign.Client;
import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;

public class PositionApiFactory {

    private PositionApiFactory() {
    }

    public static PositionApi create() {
        return create(Feign.builder());
    }

    static PositionApi create(Client client) {
        return create(Feign.builder().client(client));
    }

    @Value
    private static class PositionDto {

        String _id;
        String name;
        String type;

        Geo geo_position;

        @Value
        static class Geo {

            BigDecimal latitude;
            BigDecimal longitude;
        }

        private Position toPosition() {
            return new Position(_id, name, type, geo_position.latitude, geo_position.longitude);
        }
    }

    private interface Api {

        @RequestLine("GET /api/v2/position/suggest/en/{city}")
        List<PositionDto> getSuggestions(@Param("city") String text);
    }

    private static PositionApi create(Feign.Builder builder) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Api api = builder.decoder(new JacksonDecoder(mapper))
            .target(Api.class, "http://api.goeuro.com");

        return text -> api.getSuggestions(text).stream()
            .map(PositionDto::toPosition)
            .collect(Collectors.toList());
    }
}
