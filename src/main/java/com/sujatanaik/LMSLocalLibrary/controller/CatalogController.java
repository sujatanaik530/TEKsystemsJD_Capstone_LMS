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
import java.util.Set;

@Slf4j
@Controller
public class CatalogController {

    @Autowired
    private BookDAO bookDao;
    /**
     * This method handles the localhost:8080/goodreads
     * This is the "good reads" (recommendations) page of the library management system.
     *
     * To do: recommend 5 random books
     */

    @RequestMapping(value="/goodreads", method= RequestMethod.GET)
    public ModelAndView goodreads() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - goodreads()");

        List<Book> books = bookDao.findAll();
        response.addObject("books", books);

        response.setViewName("goodreads");

        return response;
    }

    /**
     * This method handles the localhost:8080/catalog/search.
     * This is the search page of the library management system, where a user can search the catalog by a keyword.
     */
    //@GetMapping(value="/catalog/search")
    @GetMapping(value="/user/usersearch")
    public ModelAndView search() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - search()");

        response.setViewName("user/usersearch");

        return response;
    }

    @GetMapping(value="/catalog/searchtitle")
    public ModelAndView searchByTitle(@RequestParam(required=false, name="searchT") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchByTitle()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByTitleIgnoreCaseContaining(search);
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("user/usersearch");

        return response;
    }

    @GetMapping(value="/catalog/searchauthor")
    public ModelAndView searchByAuthor(@RequestParam(required=false, name="searchA") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchByAuthor()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByAuthorIgnoreCaseContaining(search);
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("user/usersearch");

        return response;
    }

    @GetMapping(value="/catalog/searchcategory")
    public ModelAndView searchByCategory(@RequestParam(required=false, name="searchC") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchByCategory()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByCategoryIgnoreCaseContaining(search);
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("user/usersearch");

        return response;
    }
}
