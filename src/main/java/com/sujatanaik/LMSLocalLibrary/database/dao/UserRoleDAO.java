package com.sujatanaik.LMSLocalLibrary.database.dao;

import com.sujatanaik.LMSLocalLibrary.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(@Param("userId") Integer userId);
    boolean existsUserRoleByUserIdAndUserRoleEquals(@Param("user_id") Integer user_id, @Param("user_role") String userRole);
}