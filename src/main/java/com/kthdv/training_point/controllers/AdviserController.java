package com.kthdv.training_point.controllers;

import com.kthdv.training_point.constant.Constant;
import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.models.response.FormFeedback;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import com.kthdv.training_point.services.AdviserFeedbackService;
import com.kthdv.training_point.services.AdviserReceivedFormService;
import com.kthdv.training_point.services.TrainingPointFormService;
import com.kthdv.training_point.services.UserAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adviser")
public class AdviserController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private AdviserReceivedFormService adviserReceivedFormService;
    @Autowired
    private TrainingPointFormService trainingPointFormService;
    @Autowired
    private AdviserFeedbackService adviserFeedbackService;

    @ApiOperation(value = "Get all received student training point form", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success", responseContainer = "List", response = StudentTrainingPointFormPreview.class)
    })
    @GetMapping("/trainingPointForms")
    public ResponseEntity<List<StudentTrainingPointFormPreview>> getAllReceivedStudentTrainingPointForm() {
        return adviserReceivedFormService.getAllReceivedStudentTrainingPointForm();
    }

    @ApiOperation(value = "Send feedback (accept or reject)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success"),
            @ApiResponse(code = 403, message = "User not found or role isn't adviser"),
            @ApiResponse(code = 404, message = "Training point form not found, maybe id is incorrect"),
            @ApiResponse(code = 400, message = "State must be \"accepted\" or \"rejected\"")
    })
    @GetMapping("/trainingPointForm/{studentID}/state/{state}")
    public ResponseEntity sendFeedback(@RequestParam("adviserID") String adviserID,
                                       @PathVariable("studentID") String studentID,
                                       @PathVariable("state") String state) {
        if (!userAuthService.isUserValid(adviserID, User.ADVISER_ROLE)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (!trainingPointFormService.isTrainingPointFormExist(studentID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!state.equals(Constant.ACCEPTED_STATE) && !state.equals(Constant.REJECTED_STATE)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return adviserFeedbackService.submitFeedback(adviserID, studentID, state);
    }

    @ApiOperation(value = "Get all adviser feedback (api for monitor)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success", responseContainer = "List", response = FormFeedback.class)
    })
    @GetMapping("/feedback")
    public ResponseEntity<List<FormFeedback>> getAllAdviserFeedback() {
        return adviserFeedbackService.getAllAdviserFeedback();
    }

    @ApiOperation(value = "Get adviser feedback (api for student)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get training point forms success", responseContainer = "List", response = FormFeedback.class)
    })
    @GetMapping("/feedback/{studentID}")
    public ResponseEntity<FormFeedback> getAdviserFeedback(@PathVariable("studentID") String studentID) {
        if (!userAuthService.isUserValid(studentID, User.STUDENT_ROLE, User.MONITOR_ROLE)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return adviserFeedbackService.getAdviserFeedback(studentID);
    }
}
