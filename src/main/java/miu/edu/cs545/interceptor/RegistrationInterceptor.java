package miu.edu.cs545.interceptor;

import miu.edu.cs545.LayoutApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LayoutApplication.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getMethod().equals("POST")) {
            logger.info("User registered!");
        }
    }
}
