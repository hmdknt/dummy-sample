package com.kento.dummy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final static String URL = "http://gturnquist-quoters.cfapps.io/api/random";

    private final static String URL1 = "http://geoapi.heartrails.com/api/json";

    @RequestMapping(value = "/ex/exchange")
    public ResponseEntity<String> exchange() {
        return restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
    }

    @RequestMapping(value = "/ex/get-object")
    public RandomValue getObject() {
        return restTemplate.getForObject(URL, RandomValue.class);
    }

    @RequestMapping(value = "/cityInfo")
    public ResponseEntity<String> cityInfo(@RequestParam(name="method") String method) {
        return restTemplate.exchange(URL1 + "?method=" + method, HttpMethod.GET, null, String.class);
    }

    public static class RandomValue {
        public String type;
        public Value value;
    }

    public static class Value {
        public long id;
        public String quote;
    }

}
