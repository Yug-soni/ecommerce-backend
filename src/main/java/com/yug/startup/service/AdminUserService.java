package com.yug.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "admin_user";
    private final String EMAIL_FIELD = "email";
    private final String PASSWORD_FIELD = "password";

    public AdminUserService(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertAdminUser(String email, String password) {
        String sql = "INSERT INTO " + this.TABLE_NAME +
                " (" + this.EMAIL_FIELD + ", " + this.PASSWORD_FIELD + ") " +
                " VALUES ('" + email + "', '" + password + "')"
                ;
        this.jdbcTemplate.execute(sql);
        return true;
    }

    public boolean updateAdminUser(String newEmail, String newPassword, String email, String password) {
        String sql = "UPDATE "+this.TABLE_NAME+
                " SET "+this.EMAIL_FIELD+" = '"+newEmail+"', "+this.PASSWORD_FIELD+" = '"+newPassword+"' "+
                " WHERE "+this.EMAIL_FIELD+" = '"+email+"' AND "+this.PASSWORD_FIELD+" = '"+password+"'; "
                ;
        this.jdbcTemplate.execute(sql);
        return true;
    }

    public boolean deleteAdminUser(String email, String password) {
        String sql = "DELETE FROM "+this.TABLE_NAME+
                " WHERE "+this.EMAIL_FIELD+" = '"+email+"' AND "+this.PASSWORD_FIELD+" = '"+password+"';"
                ;
        this.jdbcTemplate.execute(sql);
        return true;
    }
}
