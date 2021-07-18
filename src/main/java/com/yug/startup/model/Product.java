package com.yug.startup.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter @Setter
@RequiredArgsConstructor
@ToString
public class Product {
    @Id
    private Long id;
    private String displayName;
    private String productName;
    private String mainImage;
    private String serialNumber;
}
