/*
 * Created by Bogdan Tirca on 5/9/17.
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

package com.giphy.sdk.core.models.enums;

public enum  RenditionType {
    // Original file size and file dimensions. Good for desktop use.
    original,

    /// Preview image for original.
    originalStill,

    /// File size under 50kb. Duration may be truncated to meet file size requirements. Good for thumbnails and previews.
    preview,

    /// Duration set to loop for 15 seconds. Only recommended for this exact use case.
    looping,

    /// Height set to 200px. Good for mobile use.
    fixedHeight,

    /// Static preview image for fixed_height
    fixedHeightStill,

    /// Height set to 200px. Reduced to 6 frames to minimize file size to the lowest.
    /// Works well for unlimited scroll on mobile and as animated previews. See Giphy.com on mobile web as an example.
    fixedHeightDownsampled,

    /// Height set to 100px. Good for mobile keyboards.
    fixedHeightSmall,

    /// Static preview image for fixed_height_small
    fixedHeightSmallStill,

    /// Width set to 200px. Good for mobile use.
    fixedWidth,

    /// Static preview image for fixed_width
    fixedWidthStill,

    /// Width set to 200px. Reduced to 6 frames. Works well for unlimited scroll on mobile and as animated previews.
    fixedWidthDownsampled,

    /// Width set to 100px. Good for mobile keyboards.
    fixedWidthSmall,

    /// Static preview image for fixed_width_small
    fixedWidthSmallStill,

    /// File size under 2mb.
    downsized,

    /// File size under 200kb.
    downsizedSmall,

    /// File size under 5mb.
    downsizedMedium,

    /// File size under 8mb.
    downsizedLarge,

    /// Static preview image for downsized.
    downsizedStill,
}
