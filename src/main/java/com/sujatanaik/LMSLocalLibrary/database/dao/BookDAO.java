package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.Book;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {
    public List<Book> findAll();
    public Book findById(@Param("id") Integer id);
    public List<Book> findByTitleIgnoreCaseContaining(@Param("title") String title);
    public List<Book> findByAuthorIgnoreCaseContaining(@Param("author") String author);
    public List<Book> findByCategoryIgnoreCaseContaining(@Param("category") String category);

    public Book findDistinctByTitle(String title);
}
