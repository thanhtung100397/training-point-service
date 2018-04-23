package com.kthdv.training_point.controllers;

import com.kthdv.training_point.constant.Constant;
import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.models.response.FormFeedback;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import com.kthdv.training_point.services.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/monitor")
public class MonitorController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private TrainingPointFormService trainingPointFormService;
    @Autowired
    private MonitorReceivedFormService monitorReceivedFormService;
    @Autowired
    private MonitorFeedbackService monitorFeedbackService;

    @ApiOperation(value = "Get all received student training point form", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success", responseContainer = "List", response = StudentTrainingPointFormPreview.class)
    })
    @GetMapping("/trainingPointForms")
    public ResponseEntity<List<StudentTrainingPointFormPreview>> getStudentTrainingPointForm() {
        return monitorReceivedFormService.getAllReceivedStudentTrainingPointForms();
    }

    @ApiOperation(value = "Send feedback (accept or reject)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success"),
            @ApiResponse(code = 403, message = "User not found or role isn't monitor"),
            @ApiResponse(code = 404, message = "Training point form not found, maybe id is incorrect"),
            @ApiResponse(code = 400, message = "State must be \"accepted\" or \"rejected\"")
    })
    @GetMapping("/trainingPointForm/{studentID}/state/{state}")
    public ResponseEntity sendFeedback(@RequestParam("monitorID") String monitorID,
                                       @PathVariable("studentID") String studentID,
                                       @PathVariable("state") String state) {
        if (!userAuthService.isUserValid(monitorID, User.MONITOR_ROLE)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (!trainingPointFormService.isTrainingPointFormExist(studentID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!state.equals(Constant.ACCEPTED_STATE) && !state.equals(Constant.REJECTED_STATE)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return monitorFeedbackService.submitFeedback(monitorID, studentID, state);
    }

//    @ApiOperation(value = "Get monitor feedback (null response body if not exist)", response = Iterable.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Get feedback success", response = FormFeedback.class),
//            @ApiResponse(code = 403, message = "User isn't exist or role isn't student, monitor"),
//    })
//    @GetMapping("/feedback")
//    public ResponseEntity<FormFeedback> getMonitorFeedback(@RequestParam(value = "studentID") String studentID) {
//        if (!userAuthService.isUserValid(studentID, User.STUDENT_ROLE, User.MONITOR_ROLE)) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        return monitorFeedbackService.getMonitorFeedback(studentID);
//    }
}
