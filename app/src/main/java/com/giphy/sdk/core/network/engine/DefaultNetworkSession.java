package com.giphy.sdk.core.network.engine;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.giphy.sdk.core.models.deserializers.BooleanDeserializer;
import com.giphy.sdk.core.models.deserializers.DateDeserializer;
import com.giphy.sdk.core.models.deserializers.MainAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

import com.giphy.sdk.core.network.response.ErrorResponse;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.threading.ApiTask;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class DefaultNetworkSession implements NetworkSession {
    private static final Gson GSON_INSTANCE = new GsonBuilder()
            .registerTypeHierarchyAdapter(Date.class, new DateDeserializer())
            .registerTypeHierarchyAdapter(boolean.class, new BooleanDeserializer())
            .registerTypeAdapterFactory(new MainAdapterFactory())
            .create();

    @Override
    public <T extends GenericResponse> ApiTask<T> queryStringConnection(@NonNull final Uri serverUrl,
                                                                        @NonNull final String path,
                                                                        @NonNull final String method,
                                                                        @NonNull final Class<T> responseClass,
                                                                        @Nullable final Map<String, String> queryStrings,
                                                                        @Nullable final Map<String, String> headers) {
        return new ApiTask<>(new Callable<T>() {
            @Override
            public T call() throws Exception {
                HttpURLConnection connection = null;
                try {
                    Uri.Builder uriBuilder = serverUrl.buildUpon().appendEncodedPath(path);

                    if (queryStrings != null) {
                        for (Map.Entry<String, String> query : queryStrings.entrySet()) {
                            uriBuilder.appendQueryParameter(query.getKey(), query.getValue());
                        }
                    }

                    URL url = new URL(uriBuilder.build().toString());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(method);

                    if (headers != null) {
                        for (Map.Entry<String, String> header : headers.entrySet()) {
                            connection.setRequestProperty(header.getKey(), header.getValue());
                        }
                    }

                    connection.connect();

                    return readJsonResponse(connection, responseClass);

                } catch (Throwable t) {
                    Log.e(NetworkSession.class.getName(), "Unable to perform network request", t);
                    throw t;
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
    }

    private <T extends GenericResponse> T readJsonResponse(@NonNull HttpURLConnection connection,
                                                       @NonNull Class<T> responseClass) throws IOException, ApiException {


        boolean succeeded = connection.getResponseCode() == HttpURLConnection.HTTP_OK ||
                connection.getResponseCode() == HttpURLConnection.HTTP_CREATED ||
                connection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED;
        BufferedReader inputReader;
        if (succeeded) {
            inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            inputReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        StringWriter stringWriter = new StringWriter();

        String line;
        while ((line = inputReader.readLine()) != null) {
            stringWriter.append(line);
        }

        String contents = stringWriter.toString();
        if (succeeded) {
            return GSON_INSTANCE.fromJson(contents, responseClass);
        } else {
            // Report if an invalid api key is used
            if (connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                Log.e(getClass().toString(), "Api key invalid!");
            }
            try {
                throw new ApiException(GSON_INSTANCE.fromJson(contents, ErrorResponse.class));
            } catch (JsonParseException e) {
                throw new ApiException("Unable to parse server response", new ErrorResponse(connection.getResponseCode(), contents));
            }
        }
    }
}
