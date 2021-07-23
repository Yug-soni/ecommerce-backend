package com.yug.startup.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@Getter @Setter
@RequiredArgsConstructor
@ToString
public class DealsOfTheDay {
    @Id
    private Long id;
    private Long productId;
    private String displayName;
    private String imageUrl;
    private int discount;
    private String slogan;
}
