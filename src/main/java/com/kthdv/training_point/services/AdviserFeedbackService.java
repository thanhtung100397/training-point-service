package com.kthdv.training_point.services;

import com.kthdv.training_point.constant.Constant;
import com.kthdv.training_point.dao.AdviserFeedbackRepository;
import com.kthdv.training_point.dao.AdviserReceivedFormRepository;
import com.kthdv.training_point.dao.MonitorFeedbackRepository;
import com.kthdv.training_point.models.entity.AdviserFeedback;
import com.kthdv.training_point.models.entity.MonitorFeedback;
import com.kthdv.training_point.models.response.FormFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdviserFeedbackService {
    @Autowired
    private AdviserReceivedFormRepository adviserReceivedFormRepository;
    @Autowired
    private MonitorFeedbackRepository monitorFeedbackRepository;
    @Autowired
    private AdviserFeedbackRepository adviserFeedbackRepository;

    public ResponseEntity<List<FormFeedback>> getAllAdviserFeedback() {
        return new ResponseEntity<>(adviserFeedbackRepository.getAllAdviserFeedback(), HttpStatus.OK);
    }

    public ResponseEntity<FormFeedback> getAdviserFeedback(String studentID) {
        return new ResponseEntity<>(adviserFeedbackRepository.getAdviserFormFeedback(studentID), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity submitFeedback(String formID, String state) {
        adviserReceivedFormRepository.deleteByUserID(formID);
        adviserFeedbackRepository.save(new AdviserFeedback(formID, state));
        if(state.equals(Constant.ACCEPTED_STATE)){
            monitorFeedbackRepository.save(new MonitorFeedback(formID, state));
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
