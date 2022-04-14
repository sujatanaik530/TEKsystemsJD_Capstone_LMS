package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.formbean.AddBookFormBean;
import com.sujatanaik.LMSLocalLibrary.formbean.SearchFormBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    @RequestMapping(value="/admin/admindash", method= RequestMethod.GET)
    public ModelAndView admindashboard() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - admindashboard()");
        response.setViewName("admin/admindash");
        return response;
    }
}
