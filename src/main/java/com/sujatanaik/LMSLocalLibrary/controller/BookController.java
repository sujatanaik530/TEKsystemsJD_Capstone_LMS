package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.BookDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.formbean.AddBookFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookDAO bookDao;

    /**
     * This method handles the localhost:8080/book/addbook.
     * This is the new book page of the library management system, where a librarian can add a new book to the database.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/book/addbook", method = RequestMethod.GET)
    public ModelAndView addBook() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In BookController - addBook()");

        response.setViewName("book/addbook");

        AddBookFormBean form = new AddBookFormBean();
        response.addObject("form", form);

        return response;
    }

    /**
     * This method is used for "create" or "edit", based on the "id" value.
     */
    @RequestMapping (value = "book/addbooksubmit", method = RequestMethod.POST)
    public ModelAndView addbookSubmit(@Valid AddBookFormBean form, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        log.info("In BookController - addbookSubmit()");

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error: bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            response.addObject("errorMessages", errorMessages);

            response.setViewName("book/addbook");
            return response;
        }

        // try to load the book from db using incoming id
        Book book = bookDao.findById(form.getId());

        // now check if the book is null, it means book isn't in the db. We then create a book.
        if (book == null) {
            book = new Book();
        }

        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setPrice(form.getPrice());
        book.setCategory(form.getCategory());
        //book.setCondition(form.getCondition());
        book.setCondition("Good");
        //book.setStatus(form.getStatus());
        book.setStatus(Book.BookStatus.AVAILABLE);
        book.setImg(form.getImageURL());

        bookDao.save(book);

        // form(HTML) -> form bean(form object above) -> database(book object above)

        log.info(form.toString());

        // redirect to an add book page again to add more books?

        response.setViewName("redirect:/book/addbook");

        return response;
    }
}
