package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.RenditionType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Images {
    @SerializedName("fixed_height")
    private Image fixedHeight;
    @SerializedName("fixed_height_still")
    private Image fixedHeightStill;
    @SerializedName("fixed_height_downsampled")
    private Image fixedHeightDownsampled;
    @SerializedName("fixed_width")
    private Image fixedWidth;
    @SerializedName("fixed_width_still")
    private Image fixedWidthStill;
    @SerializedName("fixed_width_downsampled")
    private Image fixedWidthDownsampled;
    @SerializedName("fixed_height_small")
    private Image fixedHeightSmall;
    @SerializedName("fixed_height_small_still")
    private Image fixedHeightSmallStill;
    @SerializedName("fixed_width_small")
    private Image fixedWidthSmall;
    @SerializedName("fixed_width_small_still")
    private Image fixedWidthSmallStill;
    private Image downsized;
    @SerializedName("downsized_still")
    private Image downsizedStill;
    @SerializedName("downsized_large")
    private Image downsizedLarge;
    @SerializedName("downsized_medium")
    private Image downsizedMedium;
    private Image original;
    @SerializedName("original_still")
    private Image originalStill;
    private Image looping;
    private Image preview;
    @SerializedName("downsized_small")
    private Image downsizedSmall;

    private String mediaId;

    public Image getFixedHeight() {
        return fixedHeight;
    }

    public void setFixedHeight(Image fixedHeight) {
        this.fixedHeight = fixedHeight;
    }

    public Image getFixedHeightStill() {
        return fixedHeightStill;
    }

    public void setFixedHeightStill(Image fixedHeightStill) {
        this.fixedHeightStill = fixedHeightStill;
    }

    public Image getFixedHeightDownsampled() {
        return fixedHeightDownsampled;
    }

    public void setFixedHeightDownsampled(Image fixedHeightDownsampled) {
        this.fixedHeightDownsampled = fixedHeightDownsampled;
    }

    public Image getFixedWidth() {
        return fixedWidth;
    }

    public void setFixedWidth(Image fixedWidth) {
        this.fixedWidth = fixedWidth;
    }

    public Image getFixedWidthStill() {
        return fixedWidthStill;
    }

    public void setFixedWidthStill(Image fixedWidthStill) {
        this.fixedWidthStill = fixedWidthStill;
    }

    public Image getFixedWidthDownsampled() {
        return fixedWidthDownsampled;
    }

    public void setFixedWidthDownsampled(Image fixedWidthDownsampled) {
        this.fixedWidthDownsampled = fixedWidthDownsampled;
    }

    public Image getFixedHeightSmall() {
        return fixedHeightSmall;
    }

    public void setFixedHeightSmall(Image fixedHeightSmall) {
        this.fixedHeightSmall = fixedHeightSmall;
    }

    public Image getFixedHeightSmallStill() {
        return fixedHeightSmallStill;
    }

    public void setFixedHeightSmallStill(Image fixedHeightSmallStill) {
        this.fixedHeightSmallStill = fixedHeightSmallStill;
    }

    public Image getFixedWidthSmall() {
        return fixedWidthSmall;
    }

    public void setFixedWidthSmall(Image fixedWidthSmall) {
        this.fixedWidthSmall = fixedWidthSmall;
    }

    public Image getFixedWidthSmallStill() {
        return fixedWidthSmallStill;
    }

    public void setFixedWidthSmallStill(Image fixedWidthSmallStill) {
        this.fixedWidthSmallStill = fixedWidthSmallStill;
    }

    public Image getDownsized() {
        return downsized;
    }

    public void setDownsized(Image downsized) {
        this.downsized = downsized;
    }

    public Image getDownsizedStill() {
        return downsizedStill;
    }

    public void setDownsizedStill(Image downsizedStill) {
        this.downsizedStill = downsizedStill;
    }

    public Image getDownsizedLarge() {
        return downsizedLarge;
    }

    public void setDownsizedLarge(Image downsizedLarge) {
        this.downsizedLarge = downsizedLarge;
    }

    public Image getDownsizedMedium() {
        return downsizedMedium;
    }

    public void setDownsizedMedium(Image downsizedMedium) {
        this.downsizedMedium = downsizedMedium;
    }

    public Image getOriginal() {
        return original;
    }

    public void setOriginal(Image original) {
        this.original = original;
    }

    public Image getOriginalStill() {
        return originalStill;
    }

    public void setOriginalStill(Image originalStill) {
        this.originalStill = originalStill;
    }

    public Image getLooping() {
        return looping;
    }

    public void setLooping(Image looping) {
        this.looping = looping;
    }

    public Image getPreview() {
        return preview;
    }

    public void setPreview(Image preview) {
        this.preview = preview;
    }

    public Image getDownsizedSmall() {
        return downsizedSmall;
    }

    public void setDownsizedSmall(Image downsizedSmall) {
        this.downsizedSmall = downsizedSmall;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * Passed down the rendition type and media id to each image
     */
    public void postProcess() {
        if (original != null) {
            original.setMediaId(mediaId);
            original.setRenditionType(RenditionType.original);
        }
        if (originalStill != null) {
            originalStill.setMediaId(mediaId);
            originalStill.setRenditionType(RenditionType.originalStill);
        }
        if (fixedHeight != null) {
            fixedHeight.setMediaId(mediaId);
            fixedHeight.setRenditionType(RenditionType.fixedHeight);
        }
        if (fixedHeightStill != null) {
            fixedHeightStill.setMediaId(mediaId);
            fixedHeightStill.setRenditionType(RenditionType.fixedHeightStill);
        }
        if (fixedHeightDownsampled != null) {
            fixedHeightDownsampled.setMediaId(mediaId);
            fixedHeightDownsampled.setRenditionType(RenditionType.fixedHeightDownsampled);
        }
        if (fixedWidth != null) {
            fixedWidth.setMediaId(mediaId);
            fixedWidth.setRenditionType(RenditionType.fixedWidth);
        }
        if (fixedWidthStill != null) {
            fixedWidthStill.setMediaId(mediaId);
            fixedWidthStill.setRenditionType(RenditionType.fixedWidthStill);
        }
        if (fixedWidthDownsampled != null) {
            fixedWidthDownsampled.setMediaId(mediaId);
            fixedWidthDownsampled.setRenditionType(RenditionType.fixedWidthDownsampled);
        }
        if (fixedHeightSmall != null) {
            fixedHeightSmall.setMediaId(mediaId);
            fixedHeightSmall.setRenditionType(RenditionType.fixedHeightSmall);
        }
        if (fixedHeightSmallStill != null) {
            fixedHeightSmallStill.setMediaId(mediaId);
            fixedHeightSmallStill.setRenditionType(RenditionType.fixedHeightSmallStill);
        }
        if (fixedWidthSmall != null) {
            fixedWidthSmall.setMediaId(mediaId);
            fixedWidthSmall.setRenditionType(RenditionType.fixedWidthSmall);
        }
        if (fixedWidthSmallStill != null) {
            fixedWidthSmallStill.setMediaId(mediaId);
            fixedWidthSmallStill.setRenditionType(RenditionType.fixedWidthSmallStill);
        }
        if (downsized != null) {
            downsized.setMediaId(mediaId);
            downsized.setRenditionType(RenditionType.downsized);
        }
        if (downsizedStill != null) {
            downsizedStill.setMediaId(mediaId);
            downsizedStill.setRenditionType(RenditionType.downsizedStill);
        }
        if (downsizedLarge != null) {
            downsizedLarge.setMediaId(mediaId);
            downsizedLarge.setRenditionType(RenditionType.downsizedLarge);
        }
        if (downsizedMedium != null) {
            downsizedMedium.setMediaId(mediaId);
            downsizedMedium.setRenditionType(RenditionType.downsizedMedium);
        }
        if (looping != null) {
            looping.setMediaId(mediaId);
            looping.setRenditionType(RenditionType.looping);
        }
        if (preview != null) {
            preview.setMediaId(mediaId);
            preview.setRenditionType(RenditionType.preview);
        }
        if (downsizedSmall != null) {
            downsizedSmall.setMediaId(mediaId);
            downsizedSmall.setRenditionType(RenditionType.downsizedSmall);
        }
    }
}
