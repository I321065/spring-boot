package com.ironman.www.spring.service.common;

/**
 * Created by Nick on 2017/4/18.
 */
public class Constants {
    //JWT
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
    public static final String RESPONSE_ERROR = "failed to handle request, please contact administrator";

}
