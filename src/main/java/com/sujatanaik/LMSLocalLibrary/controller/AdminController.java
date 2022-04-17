package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.formbean.AddBookFormBean;
import com.sujatanaik.LMSLocalLibrary.formbean.PatronRegisterFormBean;
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

    @Autowired
    BookDAO bookDao;

    @RequestMapping(value="/admin/adminsearch", method= RequestMethod.GET)
    public ModelAndView adminsearch() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminsearch()");
        response.setViewName("admin/adminsearch");
        return response;
    }

    @GetMapping(value="/admin/searchtitle")
    public ModelAndView searchByTitle(@RequestParam(required=false, name="searchT") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByTitle()");

        List<Book> books;

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByTitleIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" returned no results");
            }
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminbook");

        return response;
    }

    @GetMapping(value="/admin/searchauthor")
    public ModelAndView searchByAuthor(@RequestParam(required=false, name="searchA") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByAuthor()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByAuthorIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" returned no results");
            }
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminbook");

        return response;
    }

    @GetMapping(value="/admin/searchcategory")
    public ModelAndView searchByCategory(@RequestParam(required=false, name="searchC") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByCategory()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByCategoryIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" returned no results");
            }
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminbook");

        return response;
    }

    @RequestMapping(value="/admin/adminuser", method= RequestMethod.GET)
    public ModelAndView adminuser() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminuser()");
        response.setViewName("admin/adminuser");
        return response;
    }

    @RequestMapping(value="/admin/adminbook", method= RequestMethod.GET)
    public ModelAndView adminbook() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminbook()");
        response.setViewName("admin/adminbook");
        return response;
    }
}