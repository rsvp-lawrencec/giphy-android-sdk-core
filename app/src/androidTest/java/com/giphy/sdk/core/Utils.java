/*
 * Created by Bogdan Tirca on 4/24/17.
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

package com.giphy.sdk.core;

import com.giphy.sdk.core.models.Media;

import junit.framework.Assert;

import java.util.List;

public class Utils {
    public static long SMALL_DELAY = 2000;
    public static long MEDIUM_DELAY = 3000;

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
