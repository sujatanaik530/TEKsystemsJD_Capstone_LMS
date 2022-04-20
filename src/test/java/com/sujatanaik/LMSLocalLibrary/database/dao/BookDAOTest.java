package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {
    @Autowired
    BookDAO bookDao;

    @BeforeEach
    public void createBeforeEachTestMethod() {
        Book theBook = new Book();
        theBook.setTitle("The New Book");
        theBook.setAuthor("Sujata Naik");
        theBook.setStatus(Book.BookStatus.AVAILABLE);
        theBook.setCondition(Book.BookCondition.GOOD);
        theBook.setImg("");
        theBook.setPrice(4.99);
        theBook.setCreateDate(new Date());
        theBook.setCategory("Fiction");

        bookDao.save(theBook);

        Book theBook1 = new Book();
        theBook1.setTitle("Another New Book");
        theBook1.setAuthor("Jennifer Nelson");
        theBook1.setStatus(Book.BookStatus.AVAILABLE);
        theBook1.setCondition(Book.BookCondition.GOOD);
        theBook1.setImg("");
        theBook1.setPrice(14.99);
        theBook1.setCreateDate(new Date());
        theBook1.setCategory("Science Fiction");

        bookDao.save(theBook1);
    }

    @Order(1)
    @ParameterizedTest
    @ValueSource(strings = { "The New Book", "Another New Book" })
    public void createBookTest(String title) {
        Book checkBook = bookDao.findDistinctByTitle(title);
        assertNotNull(checkBook);
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = { "The New Book", "Another New Book" })
    public void findBookByTitleAndAvailableTest(String title) {
        List<Book> checkBook = bookDao.findBookByTitleAndAvailable(title);
        Assertions.assertThat(checkBook.get(0).getTitle().equals(title));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = { "Sujata Naik", "Jennifer Nelson" })
    public void findBookByAuthorAndAvailableTest(String author) {
        List<Book> checkBook = bookDao.findBookByAuthorAndAvailable(author);
        Assertions.assertThat(checkBook.get(0).getTitle().equals(author));
    }
}
