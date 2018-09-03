package com.butola.restconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yogibutola on 9/2/18.
 */
public class RestConsumerServiceImpl implements RestConsumerService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void processItemInfo(int itemId) {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:8080/restproducer/1", String.class);
        String responseBody = response.getBody();
    }

    private void processInformation(String responseBody) {
        System.out.println(responseBody);
    }
}
