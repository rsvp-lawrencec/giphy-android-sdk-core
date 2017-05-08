package com.giphy.sdk.core;

import com.giphy.sdk.core.models.TermSuggestion;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.engine.ApiException;
import com.giphy.sdk.core.network.response.TermSuggestionsResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdantmm on 5/8/17.
 */

public class TermSuggestionTest {
    GPHApi imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("4OMJYpPoYwVpe");
    }

    /**
     * Test if term suggestion endpoint returns data
     *
     * @throws Exception
     */
    @Test
    public void testBase() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.termSuggestions("come", new CompletionHandler<TermSuggestionsResponse>() {
            @Override
            public void onComplete(TermSuggestionsResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() > 0);

                for (TermSuggestion termSuggestion : result.getData()) {
                    Assert.assertNotNull(termSuggestion.getTerm());
                }

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if term suggestion endpoint returns data
     *
     * @throws Exception
     */
    @Test
    public void testForbidden() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final GPHApi client = new GPHApiClient("dc6zaTOxFJmzC");
        client.termSuggestions("come", new CompletionHandler<TermSuggestionsResponse>() {
            @Override
            public void onComplete(TermSuggestionsResponse result, Throwable e) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);
                Assert.assertNotNull(e.getCause());
                Assert.assertTrue(e.getCause() instanceof ApiException);
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse());
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse().getMeta());
                Assert.assertEquals(((ApiException)e.getCause()).getErrorResponse().getMeta().getStatus(), HttpURLConnection.HTTP_FORBIDDEN);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }
}
