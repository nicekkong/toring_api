package com.cplanet.toring.wrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;



public class RequestWrapper extends HttpServletRequestWrapper {

    static final String ACCESS_TOKEN = "access_token";

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getAccessToken() {
        Cookie[] cookies = getCookies();
        String accessToken = null;
        if(cookies == null) {
            return null;
        }
        for(Cookie c : cookies) {
            if(ACCESS_TOKEN.equals(c.getName())) {
                accessToken = c.getValue();
                continue;
            }
        }
        return accessToken;
    }


}
