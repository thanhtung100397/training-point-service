package com.kthdv.training_point.controllers;

import com.kthdv.training_point.models.response.StudentTrainingPointForm;
import com.kthdv.training_point.services.TrainingPointFormService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CommonController {
    @Autowired
    private TrainingPointFormService trainingPointFormService;

    @ApiOperation(value = "Get training point form detail", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get form detail success")
    })
    @GetMapping("/trainingPointForm/{id}")
    public ResponseEntity<StudentTrainingPointForm> getTrainingPointFormDetail(@PathVariable("id") String formID) {
        return trainingPointFormService.getTrainingPointFormDetail(formID);
    }
}
