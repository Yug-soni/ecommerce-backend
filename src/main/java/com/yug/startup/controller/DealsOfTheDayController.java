package com.yug.startup.controller;

import com.yug.startup.model.DealsOfTheDay;
import com.yug.startup.service.DealsOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/getDealsOfTheDay")
public class DealsOfTheDayController {

    private final DealsOfTheDayService service;

    @Autowired
    public DealsOfTheDayController(DealsOfTheDayService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<DealsOfTheDay>> getDealsOfTheDayList() {
        List<DealsOfTheDay> list = service.getDealsOfTheDayList();
//        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
