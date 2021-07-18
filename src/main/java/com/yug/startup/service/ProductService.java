package com.yug.startup.service;

import com.yug.startup.mapper.ProductMapper;
import com.yug.startup.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;

    private final String TABLE_NAME = "product";
    private final String ID = "id";
    private final String DISPLAY_NAME = "display_name";
    private final String MAIN_IMAGE = "main_image";
    private final String PRODUCT_NAME = "product_name";
    private final String SERIAL_NUMBER = "serial_number";

    public ProductService(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
    }

    public List<Product> getProductList() {
        String sql = "SELECT * FROM "+this.TABLE_NAME+" ;";
        List<Product> products = null;

        try {
            products = this.jdbcTemplate.query(sql,this.productMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

        return products;
    }

}
