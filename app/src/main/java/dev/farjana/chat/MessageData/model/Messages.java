package dev.farjana.chat.MessageData.model;

/**
 * Created by Farjana on 1/24/2018.
 */

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("code")
    @Expose
    private String code;


    public boolean getMessage() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    @Override
    public String toString() {
        return "Messages{" +
                "message='" + success + '\'' +
                ", number='" + code + '\'' +
                '}';
    }
}
