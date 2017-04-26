package com.giphy.sdk.core.models;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class Category {
    public String name;
    public String name_encoded;
    public Gif gif;
    public List<Category> subcategories;
}
