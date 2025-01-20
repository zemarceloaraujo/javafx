package com.br.testezemarcelo.util;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter {
    public static JsonSerializer<LocalDateTime> getSerializer() {
        return (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public static JsonDeserializer<LocalDateTime> getDeserializer() {
        return (json, typeOfT, context) -> {
            try {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (Exception e) {
                throw new JsonParseException(e);
            }
        };
    }
}

