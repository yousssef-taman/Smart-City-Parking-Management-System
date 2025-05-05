package com.CSED.SmartCityParking.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Repository
public class UserDAO {


    private final JdbcTemplate jdbc ;

    @Autowired
    public UserDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public User rowMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return new User(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                Role.valueOf(resultSet.getString(5).toUpperCase()),
                resultSet.getString(6)
        );
    }

    public Integer addUser(User user) {
        String sql = "INSERT INTO user(user_name , email , password , role, license) Values(? , ? , ? , ?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbc.update(
                connection ->
                {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getRole().toString());
                    ps.setString(5 , user.getLicence());
                    return ps;
                }
                , keyHolder
        );
        return (keyHolder.getKey() != null) ? keyHolder.getKey().intValue() : -1;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ? ; " ;
        return this.jdbc.queryForObject(sql, this::rowMapper, email);
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user;" ;
        return this.jdbc.query(sql, this::rowMapper);
    }


    public void deleteUser(Integer id) {
        String sql = "DELETE FROM user WHERE id = ? ;" ;
        this.jdbc.update(sql, id);

    }


    public void updateUsername(Integer id, String username) {
        String sql ="UPDATE user  SET user_name = ? WHERE id=? ;" ;
        this.jdbc.update(sql, username , id);
    }

    public void updatePassword(Integer id, String password) {
        String sql = "UPDATE user SET password = ? WHERE id= ? ;" ;
        this.jdbc.update(sql, password,id);
    }

    public void updateEmail(Integer id, String email) {
        String sql = "UPDATE user  SET email = ? WHERE id=?; ";
        this.jdbc.update(sql, email, id);
    }



}
