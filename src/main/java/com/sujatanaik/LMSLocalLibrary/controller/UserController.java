package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.*;
import com.sujatanaik.LMSLocalLibrary.database.entity.*;
import com.sujatanaik.LMSLocalLibrary.formbean.AdminUserControlFormBean;
import com.sujatanaik.LMSLocalLibrary.formbean.PatronEditFormBean;
import com.sujatanaik.LMSLocalLibrary.formbean.PatronRegisterFormBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private BorrowedBooksDAO borrowedBooksDao;

    @Autowired
    private UserBookDAO userBookDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    /**
     * This method is for editing a user's information. There is a path parameter being used to pass the userid
     * from the user to be edited.
     */
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping (value = "/user/edit", method = RequestMethod.GET) /* This is the URL part after localhost:8080. */
    public ModelAndView editUser(@RequestParam(required=false, name="email") String email) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In UserController - editUser()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user;

        // if a user is editing his/her own information
        if (email == null) {
            PatronEditFormBean form = new PatronEditFormBean();

            user = userDao.findByEmail(currentPrincipalName);

            response.addObject("form", form);

            form.setId(user.getId());
            form.setEmail(user.getEmail());
            form.setFname(user.getFirstName());
            form.setLname(user.getLastName());

            form.setPassword("");
            form.setCpassword("");

            form.setAline1(user.getAddressLine1());
            form.setAline2(user.getAddressLine2());
            form.setCity((user.getCity()));
            form.setState(user.getState());
            form.setZip(user.getZip());
            form.setPhone(user.getPhone());
            form.setGender(user.getGender().toString());
            form.setNews(user.getNews().toString());

            response.addObject("form", form);
            response.setViewName("user/useredit"); /* This is the JSP we need. */
        }
        // if an admin is editing a user's information
        else {
            AdminUserControlFormBean form = new AdminUserControlFormBean();

            user = userDao.findByEmail(email);

            form.setId(user.getId());
            form.setEmail(user.getEmail());
            form.setFname(user.getFirstName());
            form.setLname(user.getLastName());

            form.setAline1(user.getAddressLine1());
            form.setAline2(user.getAddressLine2());
            form.setCity((user.getCity()));
            form.setState(user.getState());
            form.setZip(user.getZip());
            form.setPhone(user.getPhone());

            form.setUstatus(user.getStatus().toString());

            form.setGender(user.getGender().toString());
            form.setNews(user.getNews().toString());

            form.setNews(user.getNews().toString());

            response.addObject("userform", form);
            response.setViewName("admin/adminuser");
        }

        return response;
    }

    /**
     * This method handles the localhost:8080/login/userregistrationsubmit.
     * This is the processing of the submit of the user registration page of the library management system.
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping (value = "user/usereditsubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView editSubmit(@Valid PatronEditFormBean form, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        log.info("In UserController - editSubmit()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error: bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.setViewName("user/useredit");

            response.addObject("bindingResult", bindingResult);
            response.addObject("errorMessages", errorMessages);

            return response;
        }

        // try to load the user from db using incoming id
        User user = userDao.findById(loggedInUser.getId());

        user.setAddressLine1(form.getAline1());
        user.setAddressLine2(form.getAline2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setPhone(form.getPhone());
        user.setGender(User.UserGender.valueOf(form.getGender()));

        if (form.getNews() == null) {
            user.setNews(User.UserNews.NO);
        }
        else {
            user.setNews(User.UserNews.valueOf(form.getNews()));
        }

        // encrypt the password from the form and then save it
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        user.setStatus(User.UserStatus.ACTIVE);

        // add a row for this user to the users table
        userDao.save(user);

        // form(HTML) -> form bean(form object above) -> database(user object above)

        log.info(form.toString());

        response.setViewName("redirect:/user/usersearch");
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping (value = "admin/usereditsubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adminEditSubmit(@Valid AdminUserControlFormBean form, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        log.info("In UserController - adminEditSubmit()");

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error: bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("userform", form);
            response.addObject("bindingResult", bindingResult);
            response.addObject("errorMessages", errorMessages);

            response.setViewName("admin/adminuser");

            return response;
        }

        User user = userDao.findByEmail(form.getEmail());

        // user does not exist, create a new user
        if (user == null) {
            user = new User();
        }
        user.setEmail(form.getEmail());
        user.setFirstName(form.getFname());
        user.setLastName((form.getLname()));
        user.setAddressLine1(form.getAline1());
        user.setAddressLine2(form.getAline2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setPhone(form.getPhone());
        user.setGender(User.UserGender.valueOf(form.getGender()));

        if (form.getNews() == null) {
            user.setNews(User.UserNews.NO);
        }
        else {
            user.setNews(User.UserNews.valueOf(form.getNews()));
        }

        user.setStatus(User.UserStatus.valueOf(form.getUstatus()));

        // add a row for this user to the users table
        userDao.save(user);

        log.info(form.toString());

        response.setViewName("redirect:/admin/adminuser");
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping(value="/user/usercheckouts", method= RequestMethod.GET)
    public ModelAndView checkouts() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - checkouts()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

        if (userBooks.isEmpty()) {
            response.addObject("message", "You do not have any books checked out.");
            response.setViewName("user/usercheckouts");
            return response;
        }

        response.addObject("borrowed", userBooks);

        response.setViewName("user/usercheckouts");
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping(value = "/user/checkout", method = RequestMethod.GET)
    public ModelAndView checkoutBook(@RequestParam(required=false, name="title") String title) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - checkoutBook()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        // get the book_id from the books table
        Book theBook = bookDao.findDistinctByTitle(title);

        // get the record from the borrowedbooks table
        // check if the book is already checked out
        // there should be only at most one row with that bookid as CHECKEDOUT
        // all others for that bookid should be RETURNED
        BorrowedBook borrowedBook = borrowedBooksDao.findByBookIdAndBstatus(theBook.getId(), BorrowedBook.BookStatus.CHECKEDOUT);
        if (borrowedBook != null) {
            // book already checked out
            if (borrowedBook.getUser().getId() == loggedInUser.getId()) {
                // checked out by logged in user
                response.addObject("message", "You have already checked out the book.");

                // show all checkouts for the user
                List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

                response.addObject("borrowed", userBooks);
                response.setViewName("user/usercheckouts");
                return response;
            }
            // If checked out by different user, show appropriate message and user's checkouts.
            else {
                // checked out by different user
                response.addObject("message", "This book has been checked out by another user.");

                // show all checkouts for the user
                List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

                response.addObject("borrowed", userBooks);
                response.setViewName("user/usercheckouts");
                return response;
            }

        }

        // create a new row in the userbooks table to record a checkout
        UserBook theUserCheckout = new UserBook();
        theUserCheckout.setUser(loggedInUser);
        theUserCheckout.setBook(theBook);
        theUserCheckout.setBorrowDate(LocalDate.now());
        theUserCheckout.setDueDate(LocalDate.now().plusDays(14));

        // create a new row for the borrowedbooks table to record a checkout
        BorrowedBook theCheckout = new BorrowedBook();
        theCheckout.setBook(theBook);
        theCheckout.setUser(loggedInUser);

        // borrowed today
        theCheckout.setBorrowDate(LocalDate.now());

        // checkout duration is 2 weeks
        theCheckout.setDueDate(LocalDate.now().plusDays(14));

        // mark as checked out in the borrowed books table
        theCheckout.setBstatus(BorrowedBook.BookStatus.CHECKEDOUT);

        // mark as checked out in the books table
        theBook.setStatus(Book.BookStatus.CHECKEDOUT);

        bookDao.save(theBook); // it was saving even without this
        borrowedBooksDao.save(theCheckout);
        userBookDao.save(theUserCheckout);

        List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

        response.addObject("message", "You checked out " + theBook.getTitle());

        response.addObject("borrowed", userBooks);

        response.setViewName("user/usercheckouts");
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/user/renew", method = RequestMethod.GET)
    public ModelAndView renewBook(@RequestParam(required=false, name="title") String title) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - renewBook()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        // get the book_id from the books table
        Book theBook = bookDao.findDistinctByTitle(title);

        // get borrowedbook row by userid, bookid, CHECKEDOUT status
        BorrowedBook theCheckout = borrowedBooksDao.findByUserIdAndBookIdAndAndBstatus(loggedInUser.getId(), theBook.getId(), BorrowedBook.BookStatus.CHECKEDOUT);

        // get userbook row by userid, bookid, CHECKEDOUT status
        UserBook theUserCheckout = userBookDao.findByUserIdAndBookId(loggedInUser.getId(), theBook.getId());

        // increment the return date by 14 days
        theCheckout.setDueDate(LocalDate.now().plusDays(14));
        theUserCheckout.setDueDate(LocalDate.now().plusDays(14));

        // save borrowedbooks record and userbooks record
        borrowedBooksDao.save(theCheckout);
        userBookDao.save(theUserCheckout);

        List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

        response.addObject("message", "You renewed " + theBook.getTitle() + ".");

        response.addObject("borrowed", userBooks);

        response.setViewName("user/usercheckouts");
        return response;
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/user/return", method = RequestMethod.GET)
    public ModelAndView returnBook(@RequestParam(required=false, name="title") String title) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - returnBook()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        // get the book_id from the books table
        Book theBook = bookDao.findDistinctByTitle(title);

        // get borrowedbook row by userid, bookid, CHECKEDOUT status
        BorrowedBook theCheckout = borrowedBooksDao.findByUserIdAndBookIdAndAndBstatus(loggedInUser.getId(), theBook.getId(), BorrowedBook.BookStatus.CHECKEDOUT);

        // change the borrowedbook record status to RETURNED
        theCheckout.setBstatus(BorrowedBook.BookStatus.RETURNED);

        // change the book record status to AVAILABLE
        theBook.setStatus(Book.BookStatus.AVAILABLE);

        // delete the userbook record
        userBookDao.deleteByUserIdAndBookId(loggedInUser.getId(), theBook.getId());

        // save records
        bookDao.save(theBook);
        borrowedBooksDao.save(theCheckout);

        List<UserBook> userBooks = userBookDao.findByUserId(loggedInUser.getId());

        response.addObject("message", "You returned " + theBook.getTitle() + ".");

        response.addObject("borrowed", userBooks);

        response.setViewName("user/usercheckouts");
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user/adduser", method= RequestMethod.GET)
    public ModelAndView addUser() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - addUser()");

        PatronRegisterFormBean userform = new PatronRegisterFormBean();
        response.addObject("userform", userform);

        response.setViewName("admin/adminuser");
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user/searchuserbyfirstname", method= RequestMethod.GET)
    public ModelAndView searchUserByFirstName(@RequestParam(name = "searchFN", required = false) String fname) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - searchUserByFirstName()");

        List<User> users;
        if (!StringUtils.isBlank(fname)) {
            users = userDao.findByFirstNameIgnoreCaseContaining(fname);
            if (users.isEmpty()) {
                response.addObject("message", "Your search for \"" + fname + "\" returned no results");
            }
            response.addObject("libusers", users);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", fname);

        response.setViewName("admin/adminuser");
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user/searchuserbylastname", method= RequestMethod.GET)
    public ModelAndView searchUserByLastName(@RequestParam(name = "searchLN", required = false) String lname) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - searchUserByLastName()");

        List<User> users;
        if (!StringUtils.isBlank(lname)) {
            users = userDao.findByLastNameIgnoreCaseContaining(lname);
            if (users.isEmpty()) {
                response.addObject("message", "Your search for \"" + lname + "\" returned no results");
            }
            response.addObject("libusers", users);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", lname);

        response.setViewName("admin/adminuser");
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user/searchuserbyemail", method= RequestMethod.GET)
    public ModelAndView searchUserByEmail(@RequestParam(name = "searchEmail", required = false) String email) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - searchUserByEmail()");

        User user;
        if (!StringUtils.isBlank(email)) {
            user = userDao.findByEmail(email);
            if (user == null) {
                response.addObject("message", "Your search for \"" + email + "\" returned no results");
            }
            response.addObject("libuser", user);
        }
        else {
            log.info("Search term cannot be empty!");
        }

        response.addObject("search", email);

        response.setViewName("admin/adminuser");
        return response;
    }
}
