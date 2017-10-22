package com.ironman.www.spring.service.common;

/**
 * Created by superuser on 10/22/17.
 */
public class ResponseResult {

    private Object data;

    private int errorNum;

    private String errorMsg;

    public ResponseResult() {
        this(null, 0, null);
    }

    public ResponseResult(Object data) {
        this(data, 0, null);
    }

    public ResponseResult(Object data, int errorNum, String errorMsg) {
        this.data = data;
        this.errorNum = errorNum;
        this.errorMsg = errorMsg;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
