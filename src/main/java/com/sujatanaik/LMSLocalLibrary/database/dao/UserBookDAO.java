package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookDAO extends JpaRepository<UserBook, Long> {

    void deleteByUserIdAndBookId(@Param("user_id") Integer userId, @Param("book_id") Integer bookId);

    UserBook findByUserIdAndBookId(@Param("user_id") Integer userId, @Param("book_id") Integer bookId);

//    List<UserBook> findByUserId(@Param("user_id") Integer userId);

    // Non-native or JPQL query requirement - used in viewing checkouts
    @Query(value = "SELECT u FROM UserBook u WHERE u.user.id = :user_id")
    List<UserBook> findByUserId(@Param("user_id") Integer userId);

    // Native query requirement - used to find books which have been checked out by a certain user
    @Query(value = "SELECT * FROM userbooks WHERE user_id = (SELECT id FROM users WHERE email = :email)", nativeQuery = true)
    List<UserBook> findUserBooksByEmail(@Param("email") String email);

    // Native query requirement - used to find books with a certain keyword in the title
    @Query(value = "SELECT * FROM userbooks WHERE book_id IN (SELECT id from books WHERE title LIKE %:title%)", nativeQuery = true)
    List<UserBook> findUserBooksByTitle(@Param("title") String title);

    // Native query requirement - used to find books with a certain keyword in the author's name
    @Query(value = "SELECT * FROM userbooks WHERE book_id IN (SELECT id FROM books WHERE author LIKE %:author%)", nativeQuery = true)
    List<UserBook> findUserBooksByAuthor(@Param("author") String author);
}
