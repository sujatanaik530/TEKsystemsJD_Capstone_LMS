package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.BorrowedBooksDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.UserBookDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.UserDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.*;
import com.sujatanaik.LMSLocalLibrary.formbean.PatronEditFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * This method is for editing a user's information. There is a path parameter being used to pass the userid
     * from the user to be edited.
     */
    @RequestMapping (value = "/user/edit", method = RequestMethod.GET) /* This is the URL part after localhost:8080. */
    public ModelAndView editUser() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In UserController - editUser()");

        // using authentication information, get user_id from users table
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        PatronEditFormBean form = new PatronEditFormBean();

        form.setId(loggedInUser.getId());
        form.setEmail(loggedInUser.getEmail());
        form.setFname(loggedInUser.getFirstName());
        form.setLname(loggedInUser.getLastName());
        form.setPassword("");
        form.setCpassword("");
        form.setAline1(loggedInUser.getAddressLine1());
        form.setAline2(loggedInUser.getAddressLine2());
        form.setCity((loggedInUser.getCity()));
        form.setState(loggedInUser.getState());
        form.setZip(loggedInUser.getZip());
        form.setPhone(loggedInUser.getPhone());

        response.addObject("form", form);

        // database -> form bean -> form

        response.setViewName("user/useredit"); /* This is the JSP we need. */

        return response;
    }

    /**
     * This method handles the localhost:8080/login/userregistrationsubmit.
     * This is the processing of the submit of the user registration page of the library management system.
     */
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
            response.addObject("bindingResult", bindingResult);
            response.addObject("errorMessages", errorMessages);

            response.setViewName("user/useredit");
            return response;
        }

        // try to load the user from db using incoming id
        User user = userDao.findById(loggedInUser.getId());

        // now check if the user is null, it means user isn't in the db. We then create a user.
//        if (user == null) {
//            user = new User();
//        }

        //user.setEmail(form.getEmail());
        //user.setFirstName(form.getFname());
        //user.setLastName(form.getLname());
        user.setAddressLine1(form.getAline1());
        user.setAddressLine2(form.getAline2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setPhone(form.getPhone());

        // encrypt the password from the form and then save it
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        user.setStatus(User.UserStatus.ACTIVE);

        // add a row for this user to the users table
        userDao.save(user);

        // form(HTML) -> form bean(form object above) -> database(user object above)

        log.info(form.toString());

        // TODO redirect to where?

        response.setViewName("redirect:/user/usersearch");

        return response;
    }

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

    @RequestMapping(value="/user/userdash", method= RequestMethod.GET)
    public ModelAndView userdashboard() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In UserController - userdashboard()");
        response.setViewName("user/userdash");
        return response;
    }

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
            // TODO formatting for message to be done in JSP
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
            // TODO formatting for message to be done in JSP
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
}
