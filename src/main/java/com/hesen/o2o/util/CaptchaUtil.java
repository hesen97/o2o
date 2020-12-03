package com.hesen.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CaptchaUtil {

    public static boolean checkCaptcha(HttpServletRequest request) {
        String expectedCaptcha = (String) request.getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String actualCaptcha = (String) request.getAttribute("verifyCodeActual");

        if (actualCaptcha == null || !actualCaptcha.equals(expectedCaptcha)) {
            return false;
        }
        return true;
    }
}
