package com.sujatanaik.LMSLocalLibrary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    /**
     * This method handles the /index URL.
     * This is the home page of the library management system.
     * @return response (index.jsp)
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In IndexController - index()");
        response.setViewName("index");
        return response;
    }
}
