package com.giphy.sdk.core.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

/**
 * Created by bogdantmm on 5/8/17.
 */

public class IntDeserializer implements JsonDeserializer<Integer> {
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
        if(jsonPrimitive.isString()) {
            return Integer.parseInt(json.getAsString());
        } else if (jsonPrimitive.isNumber()) {
            return json.getAsInt();
        }
        return 0;
    }
}