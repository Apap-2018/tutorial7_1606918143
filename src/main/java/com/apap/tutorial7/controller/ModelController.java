package com.apap.tutorial7.controller;


import com.apap.tutorial7.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    private ResponseEntity<String> getModels(@RequestParam(value = "factory") String factory) {
        Integer tahunIni = Calendar.getInstance().get(Calendar.YEAR);

        factory = factory.toLowerCase();
        String path = Setting.carQueryUrl + "/?cmd=getModels&make=" + factory + "&year=" + tahunIni.toString() ;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(path, HttpMethod.GET, entity, String.class);

        return res;
    }
}