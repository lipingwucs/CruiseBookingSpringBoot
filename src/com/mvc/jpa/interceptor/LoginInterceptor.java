/*
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 */

package com.mvc.jpa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.mvc.jpa.models.Passenger;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        //get Url
        String url = request.getRequestURI();
       
        //check the page which need to signin       
        if (url.indexOf("/signin") >= 0 || url.indexOf("/signup") >= 0
        		|| url.indexOf("/cruise_list") >= 0
        		|| url.indexOf("/cruise_detail") >= 0
        		|| url.indexOf("/aboutus") >= 0
        		|| url.indexOf("/home") >= 0) {
            return true;
        }
        // get session
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("currentUser");
        if (obj != null) {
        	 return true;
        }           
        
        // if page need to login, render it back to login page
        request.setAttribute("errorMsg", "you need to sign in firstly.");
        request.setAttribute("passenger", new Passenger());
        request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request,
                response);
        return false;
    }
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
    }
}
