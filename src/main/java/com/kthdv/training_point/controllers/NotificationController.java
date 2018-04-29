package com.kthdv.training_point.controllers;

import com.kthdv.training_point.models.entity.Gas;
import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.services.notification.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/gas")
@CrossOrigin(origins = "*")
public class NotificationController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/info/{gas}/{tempurature}")
    public ResponseEntity getNoti(@PathVariable("gas") double gas,
                                  @PathVariable("tempurature") double tempurature) {
        Gas g = new Gas(gas,tempurature);
        FCMService.sendNotification(restTemplate, FCMService.CLOUD_TOKEN, g);
        return new ResponseEntity(HttpStatus.OK);
    }
}