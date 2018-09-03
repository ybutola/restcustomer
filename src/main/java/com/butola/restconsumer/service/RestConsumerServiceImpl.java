package com.butola.restconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumerServiceImpl implements RestConsumerService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String processItemInfo(Long itemId) {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:8081/restproducer/" + itemId, String.class);

        if (response.getStatusCode() == HttpStatus.FOUND) {
            return processInformation(response.getBody());
        }
        return null;
    }

    private String processInformation(String responseBody) {
        System.out.println(responseBody);
        return responseBody;
    }
}
