package com.yug.startup.mapper;

import com.yug.startup.model.SliderData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SliderDataMapper implements RowMapper<SliderData> {

    @Override
    public SliderData mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        SliderData sliderData = new SliderData();

        sliderData.setId(resultSet.getLong("id"));
        sliderData.setProductId(resultSet.getLong("product_id"));
        sliderData.setImageUrl(resultSet.getString("image_url"));

        return sliderData;
    }

}
