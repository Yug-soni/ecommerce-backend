package com.yug.startup.service;

import com.yug.startup.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserService {

    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "admin_user";
    private final String EMAIL_FIELD = "email";
    private final String PASSWORD_FIELD = "password";

    public AdminUserService(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean getAdminUserList() {
        String sql = "SELECT * FROM "+this.TABLE_NAME+" ;";
        List<AdminUser> list = new ArrayList<>();
        try {
            List<Map<String, Object>> adminUser = jdbcTemplate.queryForList(sql);
            if (!adminUser.isEmpty()) {
                for (Map<String, Object> user : adminUser) {
                    for (Map.Entry<String, Object> entry : user.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        System.out.println(key + " " + value);
                    }
                    System.out.println();
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertAdminUser(String email, String password) {
        String sql = "INSERT INTO " + this.TABLE_NAME +
            " (" + this.EMAIL_FIELD + ", " + this.PASSWORD_FIELD + ") " +
            " VALUES ('" + email + "', '" + password + "')";
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateAdminUser(String newEmail, String newPassword, String email, String password) {
        String sql = "UPDATE "+this.TABLE_NAME+
                " SET "+this.EMAIL_FIELD+" = '"+newEmail+"', "+this.PASSWORD_FIELD+" = '"+newPassword+"' "+
                " WHERE "+this.EMAIL_FIELD+" = '"+email+"' AND "+this.PASSWORD_FIELD+" = '"+password+"'; "
                ;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAdminUser(String email, String password) {
        String sql = "DELETE FROM "+this.TABLE_NAME+
                " WHERE "+this.EMAIL_FIELD+" = '"+email+"' AND "+this.PASSWORD_FIELD+" = '"+password+"';"
                ;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
