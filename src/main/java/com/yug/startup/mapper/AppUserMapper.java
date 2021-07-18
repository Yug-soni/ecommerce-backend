package com.yug.startup.mapper;

import com.yug.startup.model.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppUserMapper implements RowMapper<AppUser> {

    @Override
    public AppUser mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(resultSet.getLong("id"));
        appUser.setEmail(resultSet.getString("email"));
        appUser.setPassword(resultSet.getString("password"));
        return appUser;
    }
}
