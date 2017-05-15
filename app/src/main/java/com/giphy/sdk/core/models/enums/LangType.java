/*
 * Created by Bogdan Tirca on 4/26/17.
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

import com.google.gson.annotations.SerializedName;

public enum LangType {
    @SerializedName("en")
    english("en"),

    @SerializedName("es")
    spanish("es"),

    @SerializedName("pt")
    portuguese("pt"),

    @SerializedName("id")
    indonesian("id"),

    @SerializedName("fr")
    french("fr"),

    @SerializedName("ar")
    arabic("ar"),

    @SerializedName("tr")
    turkish("tr"),

    @SerializedName("th")
    thai("th"),

    @SerializedName("vi")
    vietnamese("vi"),

    @SerializedName("de")
    german("de"),

    @SerializedName("it")
    italian("it"),

    @SerializedName("ja")
    japanese("ja"),

    @SerializedName("zh-CN")
    chineseSimplified("zh-CN"),

    @SerializedName("zh-TW")
    chineseTraditional("zh-TW"),

    @SerializedName("ru")
    russian("ru"),

    @SerializedName("ko")
    korean("ko"),

    @SerializedName("pl")
    polish("pl"),

    @SerializedName("nl")
    dutch("nl"),

    @SerializedName("ro")
    romanian("ro"),

    @SerializedName("hu")
    hungarian("hu"),

    @SerializedName("sv")
    swedish("sv"),

    @SerializedName("cs")
    czech("cs"),

    @SerializedName("hi")
    hindi("hi"),

    @SerializedName("bn")
    bengali("bn"),

    @SerializedName("da")
    danish("da"),

    @SerializedName("fa")
    farsi("fa"),

    @SerializedName("tl")
    filipino("tl"),

    @SerializedName("fi")
    finnish("fi"),

    @SerializedName("iw")
    hebrew("iw"),

    @SerializedName("ms")
    malay("ms"),

    @SerializedName("no")
    norwegian("no"),

    @SerializedName("uk")
    ukrainian("uk");

    private final String lang;

    private LangType(String lang) {
        this.lang = lang;
    }

    public String toString() {
        return this.lang;
    }
}
