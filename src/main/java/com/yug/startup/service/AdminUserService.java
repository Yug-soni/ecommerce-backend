package com.yug.startup.service;

import com.yug.startup.mapper.AdminUserMapper;
import com.yug.startup.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    private final JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "admin_user";
    private final String ID = "admin_user_id";
    private final String EMAIL_FIELD = "email";
    private final String PASSWORD_FIELD = "password";

    public AdminUserService(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AdminUser> getAdminUserList() {
        String sql = "SELECT * FROM "+this.TABLE_NAME+" ;";
        List<AdminUser> adminUserList = null;

        try {
            adminUserList = this.jdbcTemplate.query(sql, new AdminUserMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return adminUserList;
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

    public List<AdminUser> deleteAdminUser(AdminUser adminUser) {
        String sql = "DELETE FROM "+this.TABLE_NAME+
                " WHERE "+this.ID+" = "+adminUser.getAdminUserId()+";";
        System.out.println(sql);
        List<AdminUser> deletedAdminUser = null;
        try {
            deletedAdminUser = this.jdbcTemplate.query(sql, new AdminUserMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return deletedAdminUser;
    }
}