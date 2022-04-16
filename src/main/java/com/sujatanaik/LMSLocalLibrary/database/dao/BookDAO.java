package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Book findById(@Param("id") Integer id);

    List<Book> findByTitleIgnoreCaseContaining(@Param("title") String title);

    List<Book> findByAuthorIgnoreCaseContaining(@Param("author") String author);

    List<Book> findByCategoryIgnoreCaseContaining(@Param("category") String category);

    Book findDistinctByTitle(String title);

    @Query(value = "SELECT * FROM books WHERE title LIKE %:title% AND bstatus = 'AVAILABLE'", nativeQuery = true)
    List<Book> findBookByTitleAndAvailable(@Param("title") String title);

    @Query(value = "SELECT * FROM books WHERE author LIKE %:author% AND bstatus = 'AVAILABLE'", nativeQuery = true)
    List<Book> findBookByAuthorAndAvailable(@Param("author") String author);

}
