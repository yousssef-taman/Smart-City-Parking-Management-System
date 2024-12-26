package com.CSED.SmartCityParking.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "INSERT INTO users (username, password, email , role) VALUES (:username, :password, :email , :role)", nativeQuery = true)
    @Modifying
    @Transactional
    void createUser(@Param("username") String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("role") String role);


    @Query(value = "SELECT users.id ,username , `password` , email , `role` , license FROM users left join driver on users.id = driver.id ", nativeQuery = true)
    List<UserAndDriver> findAllUsers();


    @Query(value = "UPDATE users SET username = :username, password = :password, email = :email , role = :role WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void updateUser(@Param("id") Long id,
                    @Param("username") String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("role") String role);

    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteUser(@Param("id") Long id);

    @Query(value = "DELETE FROM driver WHERE id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteDriver(@Param("id") Long id);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertId();

    @Query(value = "INSERT INTO driver (id , license) VALUES (:id, :license);", nativeQuery = true)
    @Modifying
    @Transactional
    void createDriver(@Param("id") Long id,@Param("license") String license);

    @Query(value = "SELECT License FROM driver WHERE id = :id", nativeQuery = true)
    String findDriverByID(@Param("id") Long id);


    @Query(value = "SELECT users.id,username , `password` , email , `role` , license FROM users left join driver on users.id = driver.id " +
            "where users.id = :id;", nativeQuery = true)
    UserAndDriver findUserAndDriverByID(@Param("id") Long id);


    @Query(value = "UPDATE driver SET License = :license WHERE id = :id;", nativeQuery = true)
    @Modifying
    @Transactional
    void updateDriver(@Param("id") Long id , @Param("license") String license);
}

