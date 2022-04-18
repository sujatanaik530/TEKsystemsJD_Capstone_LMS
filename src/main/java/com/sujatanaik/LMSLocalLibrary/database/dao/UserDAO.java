package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findById(@Param("id") Integer id);
    User findByEmail(@Param("email") String email);
    List<User> findByFirstNameIgnoreCaseContaining(@Param("firstName") String fname);
    List<User> findByLastNameIgnoreCaseContaining(@Param("firstName") String fname);
}
