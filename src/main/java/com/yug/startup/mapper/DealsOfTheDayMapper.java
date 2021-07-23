package com.yug.startup.mapper;


import com.yug.startup.model.DealsOfTheDay;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DealsOfTheDayMapper implements RowMapper<DealsOfTheDay> {
    @Override
    public DealsOfTheDay mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        DealsOfTheDay d = new DealsOfTheDay();

        d.setId(resultSet.getLong("id"));
        d.setProductId(resultSet.getLong("product_id"));
        d.setDisplayName(resultSet.getString("display_name"));
        d.setDiscount(resultSet.getInt("discount"));
        d.setSlogan(resultSet.getString("slogan"));
        d.setImageUrl(resultSet.getString("image_url"));

        return d;
    }
}
