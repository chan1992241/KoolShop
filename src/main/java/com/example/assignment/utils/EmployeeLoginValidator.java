package com.example.assignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmployeeLoginValidator {
    public static boolean isEmployeeLogin (HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null){
            return false;
        }
        return true;
    }
}
