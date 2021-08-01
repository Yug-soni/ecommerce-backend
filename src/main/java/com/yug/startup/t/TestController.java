package com.yug.startup.t;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path = "test")
public class TestController {

    @GetMapping(path = "")
    public ResponseEntity<List<FakeData>> getData() {
        List<FakeData> list = new ArrayList<>();

        for(int i=0; i<10 ;++i) {
            list.add(new FakeData(i, i));
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
