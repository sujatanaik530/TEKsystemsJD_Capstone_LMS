package com.sujatanaik.LMSLocalLibrary.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    /**
     * This method handles the /error/404 URL which is also a 'Page Not Found' error.
     * @param request the HTTP request details
     * @return error/404
     */
    @ExceptionHandler(AccessDeniedException.class) // We don't want to tell the "bad user" that this is a protected URL.
    @RequestMapping(value = "/error/404")
    public String error404(HttpServletRequest request) {

        String originalUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        log.error("Requested URL not found : " + request.getMethod() + " " + originalUri);

        return "error/404";
    }

    /**
     * This method handles any exception which is thrown.
     * @param request the HTTP request details
     * @param ex the exception details
     * @return error/500
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();

        log.error("Error page exception: " + getRequestURL(request), ex);

        model.setViewName("error/500");

        String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

        model.addObject("message", ex.getMessage());
        model.addObject("stackTrace", stackTrace);

        if (ex.getCause() != null) {
            Throwable root = ExceptionUtils.getRootCause(ex);
            model.addObject("rootcause", root);

            String roottrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));
            model.addObject("roottrace", roottrace);
        }

        return model;
    }

    /**
     * This method is a helper method for the handleAllExceptions exception handler method.
     * @param request
     * @return string with URL
     */
    private String getRequestURL(HttpServletRequest request) {
        String result = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            result = result + "?" + request.getQueryString();
        }
        return result;
    }

    /**
     * This method is a helper method for the handleAllExceptions exception handler method.
     * @param stack
     * @return part of the stack trace
     */
    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();

        for (String frame: stack) {
            if (frame.contains("teksystems")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            }
            else if (frame.contains("Caused by:")) {
                result.append("Caused By: " + frame + "<br>");
            }
        }
        return result.toString();
    }
}
