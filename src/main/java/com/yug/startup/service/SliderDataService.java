package com.yug.startup.service;

import com.yug.startup.mapper.SliderDataMapper;
import com.yug.startup.model.SliderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SliderDataService {

    private final JdbcTemplate jdbcTemplate;
    private final SliderDataMapper mapper;
    private final String TABLE_NAME = "slider_data";

    @Autowired
    public SliderDataService(JdbcTemplate jdbcTemplate, SliderDataMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    public List<SliderData> getSliderData() {
        List<SliderData> list = null;
        String sql = "SELECT * FROM " + this.TABLE_NAME + " ;";
//        System.out.println(sql);
        try {
            list = this.jdbcTemplate.query(sql, mapper);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
