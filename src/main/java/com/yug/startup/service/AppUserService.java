package com.yug.startup.service;

import com.yug.startup.mapper.AppUserMapper;
import com.yug.startup.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final JdbcTemplate jdbcTemplate;
    private final AppUserMapper appUserMapper;
    private final String TABLE_NAME = "app_user";

    public AppUserService(@Autowired JdbcTemplate jdbcTemplate, @Autowired AppUserMapper appUserMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserMapper = appUserMapper;
    }

    public List<AppUser> getAppUserList() {
        String sql = "SELECT * FROM "+this.TABLE_NAME+" ;";
        List<AppUser> appUsers = null;
        try{
            appUsers = this.jdbcTemplate.query(sql, this.appUserMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return appUsers;
    }

}
