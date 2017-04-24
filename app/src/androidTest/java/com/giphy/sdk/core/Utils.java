package com.giphy.sdk.core;

import com.giphy.sdk.core.models.Gif;

import junit.framework.Assert;

import java.util.List;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class Utils {
    /**
     * Test if result2 is offseted compared to result1. Because the endpoint is not deterministic,
     * we ignore the offset value
     * @param result1
     * @param result2
     * @return
     */
    public static void checkOffsetWorks(List<Gif> result1, List<Gif> result2) {
        // We first find the exact offset
        int offset = 0;
        for (int i = 0; i < result1.size(); i ++) {
            if (result1.get(i).id.equals(result2.get(0).id)) {
                offset = i;
                break;
            }
        }
        Assert.assertTrue(offset != 0);

        // Check if all results starting from offset match with result2
        for (int i = 0; i < result2.size() && i + offset < result1.size(); i ++) {
            Assert.assertEquals(result1.get(i + offset).id, result2.get(i).id);
        }
    }
}
