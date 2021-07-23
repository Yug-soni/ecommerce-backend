package com.yug.startup.controller;

import com.yug.startup.model.SliderData;
import com.yug.startup.service.SliderDataService;
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
@RequestMapping("/getSliderData")
public class SliderDataController {

    private final SliderDataService sliderDataService;

    @Autowired
    public SliderDataController(SliderDataService sliderDataService) {
        this.sliderDataService = sliderDataService;
    }

    @GetMapping("")
    public ResponseEntity<List<SliderData>> getSliderData() {
        List<SliderData> list = this.sliderDataService.getSliderData();
//        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
