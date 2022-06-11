package com.example.bookManager.repositories;

import com.example.bookManager.domain.UserDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDetail, Integer>
{
    @Query(value = "SELECT * FROM user_detail us WHERE us.username = :username", nativeQuery = true)
    UserDetail findUserByUserName(@Param("username") String username);

    @Query(value = "SELECT * FROM user_detail us WHERE us.username = :username and us.password = :password ", nativeQuery = true)
    UserDetail findUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password );

    UserDetail findByUsername(String username);

    List<UserDetail> findAll();
}