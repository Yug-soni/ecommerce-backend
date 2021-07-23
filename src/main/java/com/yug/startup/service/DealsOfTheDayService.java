package com.yug.startup.service;

import com.yug.startup.mapper.DealsOfTheDayMapper;
import com.yug.startup.model.DealsOfTheDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealsOfTheDayService {

    private final JdbcTemplate jdbcTemplate;
    private final DealsOfTheDayMapper mapper;

    private final String TABLE_NAME = "deals_of_the_day";

    @Autowired
    public DealsOfTheDayService(JdbcTemplate jdbcTemplate, DealsOfTheDayMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    public List<DealsOfTheDay> getDealsOfTheDayList() {
        List<DealsOfTheDay> list = null;
        String sql = "SELECT * FROM " + this.TABLE_NAME + " ;";
        try {
            list = this.jdbcTemplate.query(sql, this.mapper);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
