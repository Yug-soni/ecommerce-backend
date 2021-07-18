package com.yug.startup.mapper;

import com.yug.startup.model.AdminUser;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdminUserMapper implements RowMapper<AdminUser> {

    @Override
    public AdminUser mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(resultSet.getLong("id"));
        adminUser.setEmail(resultSet.getString("email"));
        adminUser.setPassword(resultSet.getString("password"));
        return adminUser;
    }
}
