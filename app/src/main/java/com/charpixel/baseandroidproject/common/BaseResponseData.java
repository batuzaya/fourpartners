package com.charpixel.baseandroidproject.common;

/**
 * Created by ashu on 08-03-2016.
 */
public class BaseResponseData<T> {


    int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    int flag;

    String log;

    String message;
    int addCard;

    T data;


    String error;

    public int getAddCard() {
        return addCard;
    }

    public void setAddCard(int addCard) {
        this.addCard = addCard;
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
