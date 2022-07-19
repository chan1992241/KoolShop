package com.example.assignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomerLoginValidator {
    public static boolean isCustomerLogin(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if (session == null) {
            return false;
        }
        return true;
    }
}
