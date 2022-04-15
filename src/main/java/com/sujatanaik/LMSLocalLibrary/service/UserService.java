package com.sujatanaik.LMSLocalLibrary.service;

import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserService {
    public abstract User findById(@Param("id") Integer id);
    public abstract User findByEmail(@Param("email") String email);
}
