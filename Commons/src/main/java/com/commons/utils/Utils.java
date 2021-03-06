package com.commons.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static String getHost(HttpServletRequest request) {

        return removeServletPath(request.getRequestURL().toString(), request.getServletPath());
    }

    private static String removeServletPath(String hostWithPath, String servletPath) {

        StringBuilder sb = new StringBuilder(hostWithPath);
        sb.delete(hostWithPath.lastIndexOf(servletPath), 42);

        return sb.toString();
    }

}

