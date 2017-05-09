package com.giphy.sdk.core;

import com.giphy.sdk.core.models.Media;

import junit.framework.Assert;

import java.util.List;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class Utils {

    public static void checkOffsetWorks(List<Media> result1, List<Media> result2) {
        checkOffsetWorks(result1, result2, 20);
    }
    /**
     * Test if result2 is offseted compared to result1. Because the endpoint is not deterministic,
     * we ignore the offset value
     * @param result1
     * @param result2
     * @param maxLength
     * @return
     */
    public static void checkOffsetWorks(List<Media> result1, List<Media> result2, int maxLength) {
        // We first find the exact offset
        int offset = 0;
        for (int i = 0; i < result1.size(); i ++) {
            if (result1.get(i).getId().equals(result2.get(0).getId())) {
                offset = i;
                break;
            }
        }
        Assert.assertTrue(offset != 0);

        // Check if all results starting from offset match with result2
        for (int i = 0; i < result2.size() && i + offset < result1.size() && i < maxLength; i ++) {
            Assert.assertEquals(result1.get(i + offset).getId(), result2.get(i).getId());
        }
    }
}
