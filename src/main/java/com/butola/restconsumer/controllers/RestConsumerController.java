package com.butola.restconsumer.controllers;

import com.butola.restconsumer.service.RestCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yogibutola on 9/2/18.
 */
@RestController
@RequestMapping ("/restconsumer")
public class RestConsumerController {

    @Autowired
    RestCustomerService restCustomerService;

    @GetMapping (value = "/{itemID}")
    public void processItemInfo(@PathVariable int itemID){
        restCustomerService.processItemInfo(itemID);
    }
}
