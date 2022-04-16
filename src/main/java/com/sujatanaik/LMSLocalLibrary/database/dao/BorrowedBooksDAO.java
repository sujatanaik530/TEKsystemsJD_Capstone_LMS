package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowedBooksDAO extends JpaRepository<BorrowedBook, Long> {

    List<BorrowedBook> findByUserId(@Param("user_id") Integer id);

    boolean existsByUserIdAndBookIdAndAndBstatus(@Param("user_id") Integer uid, @Param("book_id") Integer bid, @Param("bstatus") BorrowedBook.BookStatus bstatus);

    BorrowedBook findByUserIdAndBookIdAndAndBstatus(@Param("user_id") Integer uid, @Param("book_id") Integer bid, @Param("bstatus") BorrowedBook.BookStatus bstatus);

    BorrowedBook findByBookIdAndBstatus(@Param("book_id") Integer bid, @Param("bstatus") BorrowedBook.BookStatus bstatus);

}
