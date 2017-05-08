package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 5/4/17.
 */

public class Meta {
    private int status;
    private String msg;
    @SerializedName("response_id")
    private String responseId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
}
