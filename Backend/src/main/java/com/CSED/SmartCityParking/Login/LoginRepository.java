package com.CSED.SmartCityParking.Login;

import com.CSED.SmartCityParking.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findUserByUserName(@Param("username") String username);
}
