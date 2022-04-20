package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {
    @Autowired
    UserDAO userDao;

    @BeforeEach
    public void createBeforeEachTestMethod() {
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
        theUser.setGender(User.UserGender.FEMALE);
        theUser.setNews(User.UserNews.YES);

        userDao.save(theUser);
    }

    @Order(1)
    @Test
    public void createUserTest() {
        User theUser = userDao.findByEmail("sn@gmail.com");
        assertNotNull(theUser);
    }

    @Order(2)
    @Test
    public void getUserByFirstNameTest() {
        List<User> theUser = userDao.findByFirstNameIgnoreCaseContaining("Sujata");
        assertNotNull(theUser.get(0));
    }

    @Order(3)
    @Test
    public void updateTest() {
        User theUser = userDao.findByEmail("sn@gmail.com");
        theUser.setZip("11111");
        Assertions.assertThat(userDao.findByEmail("sn@gmail.com").getZip().equals(theUser.getZip()));
    }

    @Order(4)
    @Test
    public void deleteTest() {
        User theUser = userDao.findByEmail("sn@gmail.com");
        userDao.delete(theUser);
        assertNull(userDao.findByEmail("sn@gmail.com"));
    }
}
