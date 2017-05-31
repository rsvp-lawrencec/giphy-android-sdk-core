/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.giphy.sdk.core.threading;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.giphy.sdk.core.network.api.CompletionHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Represents a top level task returned by API calls that can be ran async or immediately
 * with an ExecutorService.
 *
 * @see AsyncTask
 * @see ExecutorService
 */
public class ApiTask<V> {

    /**
     * HTTP Executor settings borrowed from Bolts-Android's AndroidExecutors.newCachedThreadPool
     * https://github.com/BoltsFramework/Bolts-Android/blob/master/bolts-tasks/src/main/java/bolts/AndroidExecutors.java
     */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int THREAD_POOL_CORE_SIZE = CPU_COUNT + 2;
    public static final int THREAD_POOL_MAX_SIZE = CPU_COUNT * 2 + 2;
    public static final long THREAD_POOL_KEEP_ALIVE_TIME = 1L;
    public static final ExecutorService THREAD_POOL_EXECUTOR_SERVICE = new ThreadPoolExecutor(
            THREAD_POOL_CORE_SIZE,
            THREAD_POOL_MAX_SIZE,
            THREAD_POOL_KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()
    );

    public static final Handler MAIN_LOOP_HANDLER = new Handler(Looper.getMainLooper());

    private final Callable<V> callable;

    public ApiTask(Callable<V> callable) {
        this.callable = callable;
    }

    /**
     * Resolves the task on a shared thread pool executor service and returns the result using the
     * completionHandler
     *
     * @param completionHandler
     * @return
     */
    public Future executeAsyncTask(final CompletionHandler<V> completionHandler) {
        return THREAD_POOL_EXECUTOR_SERVICE.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    final V value = callable.call();
                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(value, null);
                        }
                    });
                } catch (final ExecutionException e) {
                    Log.e(ApiTask.class.getName(), "Unable to perform async task, cancelling…", e);

                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(null, e);
                        }
                    });
                } catch (InterruptedException e) { // interrupts will naturally occur from cancelling
                } catch (final Exception e) {
                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(null, e);
                        }
                    });
                }
            }
        });
    }

    /**
     * Immediately resolves the task on a shared thread pool executor service.
     *
     * @return The resolved value from running the task.
     * @throws Exception
     */
    public V executeImmediately() throws Exception {
        return callable.call();
    }
}