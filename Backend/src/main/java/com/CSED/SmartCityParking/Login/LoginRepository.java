package com.CSED.SmartCityParking.Login;

import com.CSED.SmartCityParking.User.User;
import com.CSED.SmartCityParking.User.UserAndDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT users.id ,user_name , `password` , email , `role` , license FROM users left join driver on users.id = driver.id " +
            "where users.username = :username;", nativeQuery = true)
    UserAndDriver findUserAndDriverByUserName(@Param("username") String username);

    @Query(value = "SELECT users.id ,user_name , `password` , email , `role` , license FROM users left join driver on users.id = driver.id " +
            "where users.email = :email;", nativeQuery = true)
    UserAndDriver findUserAndDriverByEmail(@Param("email") String email);
}
