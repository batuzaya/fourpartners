package com.charpixel.baseandroidproject.common;

import android.support.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ashu on 18-07-2016.
 */
public class UploadFileService {
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }
}
