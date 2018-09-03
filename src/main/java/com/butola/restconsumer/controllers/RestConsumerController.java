package com.butola.restconsumer.controllers;

import com.butola.restconsumer.service.RestConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restconsumer")
public class RestConsumerController {

    @Autowired
    RestConsumerServiceImpl restConsumerService;

    @GetMapping(value = "/{itemID}")
    public String processItemInfo(@PathVariable Long itemID) {
        return restConsumerService.processItemInfo(itemID);
    }
}
