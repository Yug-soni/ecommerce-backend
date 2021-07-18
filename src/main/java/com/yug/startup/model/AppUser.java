package com.yug.startup.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@Getter @Setter
@RequiredArgsConstructor
@ToString
public class AppUser {

    @Id
    private Long id;
    private String email;
    private String password;
}