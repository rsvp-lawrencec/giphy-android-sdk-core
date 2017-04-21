package com.giphy.sdk.core.network.api;

/**
 * Created by bogdantmm on 4/20/17.
 */

public interface CompletionHandler <T> {
    public void onComplete(Throwable e, T result);
}
