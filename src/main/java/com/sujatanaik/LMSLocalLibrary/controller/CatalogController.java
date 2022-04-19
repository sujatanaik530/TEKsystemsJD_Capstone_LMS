package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.UserBookDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.UserDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import com.sujatanaik.LMSLocalLibrary.database.entity.UserBook;
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
public class CatalogController {

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private UserBookDAO userBookDao;

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
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" returned no results");
            }
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
            if (books.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" returned no results");
            }
            response.addObject("books", books);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("user/usersearch");

        return response;
    }

    @GetMapping(value="/catalog/checkedoutbyuser")
    public ModelAndView searchCheckedOutByUser(@RequestParam(required=false, name="search") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchCheckedOutByUser()");

        //User theUser = userDao.findByEmail(search);

        List<UserBook> userbooks = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            userbooks = userBookDao.findUserBooksByEmail(search);
            if (userbooks.isEmpty()) {
                response.addObject("message", "Your search for books checked out by \"" + search + "\" returned no results");
            }
            response.addObject("userbooks", userbooks);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        userbooks.forEach(ub -> log.info(ub.getBook().getTitle()));

        response.addObject("message", userbooks.stream().count() + " book(s) checked out by user.");

        response.addObject("search", search);

        response.setViewName("admin/adminsearch");

        return response;
    }

    @GetMapping(value="/catalog/availabletitles")
    public ModelAndView searchAvailableTitles(@RequestParam(required=false, name="search") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchAvailableTitles()");

        List<Book> availablebooks = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            availablebooks = bookDao.findBookByTitleAndAvailable(search);
            if (availablebooks.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in available books returned no results");
            }
            response.addObject("availablebooks", availablebooks);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminsearch");

        return response;
    }

    @GetMapping(value="/catalog/checkedouttitles")
    public ModelAndView searchCheckedOutTitles(@RequestParam(required=false, name="search") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchCheckedOutTitles()");

        List<UserBook> checkedoutBook;

        if (!StringUtils.isBlank(search)) {
            checkedoutBook = userBookDao.findUserBooksByTitle(search);
            if (checkedoutBook.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in checked out books returned no results");
            }
            response.addObject("checkedoutuserbooks", checkedoutBook);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminsearch");

        return response;
    }

    @GetMapping(value="/catalog/availableauthors")
    public ModelAndView searchAvailableAuthors(@RequestParam(required=false, name="search") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchAvailableAuthors()");

        List<Book> availablebooks = new ArrayList<>();

        if (!StringUtils.isBlank(search)) {
            availablebooks = bookDao.findBookByAuthorAndAvailable(search);
            if (availablebooks.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in available books returned no results");
            }
            response.addObject("availableauthors", availablebooks);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminsearch");

        return response;
    }

    @GetMapping(value="/catalog/checkedoutauthors")
    public ModelAndView searchCheckedOutAuthors(@RequestParam(required=false, name="search") String search) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In CatalogController - searchCheckedOutAuthors()");

        List<UserBook> checkedoutBook;

        if (!StringUtils.isBlank(search)) {
            checkedoutBook = userBookDao.findUserBooksByAuthor(search);
            if (checkedoutBook.isEmpty()) {
                response.addObject("message", "Your search for \"" + search + "\" in checked out books returned no results");
            }
            response.addObject("checkedoutauthors", checkedoutBook);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", search);

        response.setViewName("admin/adminsearch");

        return response;
    }
}
