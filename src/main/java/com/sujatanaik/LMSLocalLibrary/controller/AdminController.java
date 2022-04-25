package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    BookDAO bookDao;

    /**
     * This method handles the admin/adminsearch URL which shows the admin/adminsearch.jsp file.
     * @return admin/adminsearch
     * @throws Exception
     */
    @RequestMapping(value="/admin/adminsearch", method= RequestMethod.GET)
    public ModelAndView adminSearch() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminSearch()");
        response.setViewName("admin/adminsearch");
        return response;
    }

    /**
     * This method handles the admin/searchtitle URL which may have a search term for searching from the
     * books table by title containing the search term.
     * This URL originates in admin/adminbook.jsp, where the admin might search for a book by title.
     * @param search term in titles of books table
     * @return admin/adminbook
     * @throws Exception
     */
    @GetMapping(value="/admin/searchtitle")
    public ModelAndView searchByTitle(@RequestParam(required=false, name="searchT") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByTitle()");

        List<Book> books;

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByTitleIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in book titles returned no results.");
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

    /**
     * This method handles the admin/searchauthor URL which may have a search term for searching from the
     * books table by author name containing the search term.
     * This URL originates in admin/adminbook.jsp, where the admin might search for a book by author.
     * @param search term in author names of books table
     * @return admin/adminbook
     * @throws Exception
     */
    @GetMapping(value="/admin/searchauthor")
    public ModelAndView searchByAuthor(@RequestParam(required=false, name="searchA") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByAuthor()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByAuthorIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in author names returned no results.");
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

    /**
     * This method handles the admin/searchcategory URL which may have a search term for searching from the
     * books table by category name containing the search term.
     * This URL originates in admin/adminbook.jsp, where the admin might search for a book by category.
     * @param search term in category names of books table
     * @return admin/adminbook
     * @throws Exception
     */
    @GetMapping(value="/admin/searchcategory")
    public ModelAndView searchByCategory(@RequestParam(required=false, name="searchC") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In AdminController - searchByCategory()");

        List<Book> books = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            books = bookDao.findByCategoryIgnoreCaseContaining(search);
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in category names returned no results.");
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

    /**
     * This method handles the admin/adminuser URL. The rest of the functionality on the JSP is in the UserController class.
     * @return admin/adminuser
     * @throws Exception
     */
    @RequestMapping(value="/admin/adminuser", method= RequestMethod.GET)
    public ModelAndView adminUser() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminUser()");
        response.setViewName("admin/adminuser");
        return response;
    }

    /**
     * This method handles the admin/adminbook URL. The rest of the functionality on the JSP is in the BookController class.
     * @return admin/adminbook
     * @throws Exception
     */
    @RequestMapping(value="/admin/adminbook", method= RequestMethod.GET)
    public ModelAndView adminBook() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In AdminController - adminBook()");
        response.setViewName("admin/adminbook");
        return response;
    }
}