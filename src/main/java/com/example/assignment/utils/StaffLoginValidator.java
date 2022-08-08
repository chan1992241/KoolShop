package com.example.assignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StaffLoginValidator {
    public static boolean isStaffLogin(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if (!session.getAttribute("staff_no").equals("")) {
            return true;
        }
        return false;
    }
}
