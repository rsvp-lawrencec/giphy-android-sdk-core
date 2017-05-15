/*
 * Created by Bogdan Tirca on 4/25/17.
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

package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category implements Parcelable {
    private String name;
    @SerializedName("name_encoded")
    private String nameEncoded;
    private Media gif;
    @SerializedName("subcategories")
    private List<Category> subCategories;

    public Category() {}

    public Category(Parcel in) {
        name = in.readString();
        nameEncoded = in.readString();
        gif = in.readParcelable(Media.class.getClassLoader());
        subCategories = in.createTypedArrayList(Category.CREATOR);
    }

    public Category(String name, String nameEncoded) {
        this.name = name;
        this.nameEncoded = nameEncoded;
    }

    /**
     * @return category name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return encoded category name, used for constructing the URL to fetch gifs
     */
    public String getNameEncoded() {
        return nameEncoded;
    }

    public void setNameEncoded(String nameEncoded) {
        this.nameEncoded = nameEncoded;
    }

    /**
     * @return preview gif of the category
     */
    public Media getGif() {
        return gif;
    }

    public void setGif(Media gif) {
        this.gif = gif;
    }

    /**
     * @return subcategories of the category
     */
    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(nameEncoded);
        parcel.writeParcelable(gif, i);
        parcel.writeTypedList(subCategories);
    }
}
