package com.butola.restconsumer.controllers;

import com.butola.restconsumer.service.RestConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yogibutola on 9/2/18.
 */
@RestController
@RequestMapping("/restconsumer")
public class RestConsumerController {

    @Autowired
    RestConsumerService restConsumerService;

    @GetMapping(value = "/{itemID}")
    public void processItemInfo(@PathVariable int itemID) {
        restConsumerService.processItemInfo(itemID);
    }
}
