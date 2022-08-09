package com.example.assignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLoginValidator {
    public static boolean isAdminLogin (HttpServletRequest request){
        HttpSession session = request.getSession(false);
        try {
            if (!session.getAttribute("username").equals("")) {
                return true;
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
