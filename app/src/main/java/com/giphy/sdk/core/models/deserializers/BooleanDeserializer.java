package com.giphy.sdk.core.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bogdantmm on 5/8/17.
 */

public class BooleanDeserializer implements JsonDeserializer<Boolean> {
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
        if(jsonPrimitive.isBoolean()) {
            return json.getAsBoolean();
        } else if (jsonPrimitive.isNumber()) {
            return json.getAsInt() != 0;
        }
        return false;
    }
}