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
    private final AdminUserMapper adminUserMapper;
    private final String TABLE_NAME = "admin_user";
    private final String ID = "id";
    private final String EMAIL_FIELD = "email";
    private final String PASSWORD_FIELD = "password";

    public AdminUserService(@Autowired JdbcTemplate jdbcTemplate, @Autowired AdminUserMapper adminUserMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.adminUserMapper = adminUserMapper;
    }

    public List<AdminUser> getAdminUserList() {
        String sql = "SELECT * FROM "+this.TABLE_NAME+" ;";
        List<AdminUser> adminUserList = null;

        try {
            adminUserList = this.jdbcTemplate.query(sql, this.adminUserMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return adminUserList;
    }

    public boolean insertAdminUser(AdminUser adminUser) {
        String sql = "INSERT INTO " + this.TABLE_NAME +
            " (" + this.EMAIL_FIELD + ", " + this.PASSWORD_FIELD + ") " +
            " VALUES ('" + adminUser.getEmail() + "', '" + adminUser.getPassword() + "')";
        if(this.userExists(adminUser)) return false;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateAdminUser(AdminUser adminUser) {
        String sql = "UPDATE "+this.TABLE_NAME+
                " SET "+this.EMAIL_FIELD+" = '"+adminUser.getEmail()+"', "+this.PASSWORD_FIELD+" = '"+adminUser.getPassword()+"' "+
                " WHERE "+this.ID+" = "+adminUser.getId()+"; "
                ;
        try {
            this.jdbcTemplate.query(sql, this.adminUserMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAdminUser(AdminUser adminUser) {
        String sql = "DELETE FROM "+this.TABLE_NAME+
                " WHERE "+this.ID+" = "+adminUser.getId()+";";
        try {
            this.jdbcTemplate.query(sql, this.adminUserMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean userExists(AdminUser adminUser) {
        String sql = "SELECT * FROM "+this.TABLE_NAME+
                " WHERE "+this.EMAIL_FIELD+" = '"+adminUser.getEmail()+"' ;"
                ;
        List<AdminUser> list = jdbcTemplate.query(sql, this.adminUserMapper);
        return list.size() > 0;
    }
}