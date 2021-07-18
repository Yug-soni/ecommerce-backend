package com.yug.startup.mapper;

import com.yug.startup.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setSerialNumber(resultSet.getString("serial_number"));
        product.setMainImage(resultSet.getString("main_image"));
        product.setProductName(resultSet.getString("product_name"));
        product.setDisplayName(resultSet.getString("display_name"));
        return product;
    }
}
