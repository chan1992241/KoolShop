package com.example.assignment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomerLoginValidator {
    public static boolean isCustomerLogin(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        try  {
            if (session != null ) {
                if (!session.getAttribute("customer_number").equals("")){
                    return true;
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
