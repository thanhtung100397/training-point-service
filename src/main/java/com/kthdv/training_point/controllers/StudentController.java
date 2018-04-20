package com.kthdv.training_point.controllers;

import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.services.TrainingPointFormService;
import com.kthdv.training_point.services.UserAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private TrainingPointFormService trainingPointFormService;

    @ApiOperation(value = "Submit new (if not exist) or Update (if exist) training point form (send form to monitor)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Submit form success"),
            @ApiResponse(code = 403, message = "User isn't exist or role isn't student, monitor")
    })
    @PostMapping("/trainingPoint")
    public ResponseEntity submitTrainingPointForm(@RequestParam("userID") String userID,
                                                  @RequestBody Map<String, Integer> formData) {
        if (!userAuthService.isUserValid(userID, User.STUDENT_ROLE, User.MONITOR_ROLE)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return trainingPointFormService.submitForm(userID, formData);
    }
}
