package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import com.sujatanaik.LMSLocalLibrary.database.entity.UserBook;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserBookDAOTest {
    @Autowired
    UserBookDAO userBookDao;

    @Autowired
    BookDAO bookDao;

    @Autowired
    UserDAO userDao;

    @BeforeEach
    public void createBeforeEachTestMethod() {
        Book theBook = new Book();
        theBook.setTitle("The New Book");
        theBook.setAuthor("Sujata Naik");
        theBook.setStatus(Book.BookStatus.CHECKEDOUT);
        theBook.setCondition(Book.BookCondition.GOOD);
        theBook.setImg("");
        theBook.setPrice(12.99);
        theBook.setCategory("Fiction");

        bookDao.save(theBook);

        User theUser = new User();
        theUser.setEmail("sn@gmail.com");
        theUser.setPassword("testtest123");
        theUser.setStatus(User.UserStatus.ACTIVE);
        theUser.setFirstName("Sujata");
        theUser.setLastName("Naik");
        theUser.setCity("City Name");
        theUser.setAddressLine1("123 Some St");
        theUser.setZip("11111");
        theUser.setPhone("1111111111");
        theUser.setState("Minnesota");

        userDao.save(theUser);

        UserBook checkedOut = new UserBook();
        checkedOut.setDueDate(LocalDate.now());
        checkedOut.setBook(theBook);
        checkedOut.setUser(theUser);
        checkedOut.setBorrowDate(LocalDate.now());

        userBookDao.save(checkedOut);
    }

    @Order(1)
    public void findByUserId() {
        User theUser = userDao.findByEmail("sn@gmail.com");
        List<UserBook> theCheckout = userBookDao.findByUserId(theUser.getId());
        assertNotNull(theCheckout);
    }

    @Order(2)
    public void findUserBooksByEmail() {
        List<UserBook> theCheckout = userBookDao.findUserBooksByEmail("sn@gmail.com");
        assertNotNull(theCheckout);
    }

    @Order(3)
    public void findUserBooksByTitle() {
        List<UserBook> theCheckout = userBookDao.findUserBooksByTitle("The New Book");
        assertNotNull(theCheckout);
    }

    @Order(4)
    public void findUserBooksByAuthor() {
        List<UserBook> theCheckout = userBookDao.findUserBooksByAuthor("Sujata Naik");
        assertNotNull(theCheckout);
    }
}
