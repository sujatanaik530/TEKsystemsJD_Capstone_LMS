package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.BorrowedBook;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import com.sujatanaik.LMSLocalLibrary.database.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserBookDAO extends JpaRepository<UserBook, Long> {
    void deleteByUserIdAndBookId(@Param("user_id") Integer userId, @Param("book_id") Integer bookId);
    UserBook findByUserIdAndBookId(@Param("user_id") Integer userId, @Param("book_id") Integer bookId);
    List<UserBook> findByUserId(@Param("user_id") Integer userId);
}
