package com.kthdv.training_point.services;

import com.kthdv.training_point.constant.Constant;
import com.kthdv.training_point.dao.AdviserFeedbackRepository;
import com.kthdv.training_point.dao.AdviserReceivedFormRepository;
import com.kthdv.training_point.dao.MonitorFeedbackRepository;
import com.kthdv.training_point.dao.MonitorReceivedFormRepository;
import com.kthdv.training_point.models.entity.AdviserReceivedForm;
import com.kthdv.training_point.models.entity.MonitorFeedback;
import com.kthdv.training_point.models.response.FormFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonitorFeedbackService {
    @Autowired
    private MonitorReceivedFormRepository monitorReceivedFormRepository;
    @Autowired
    private MonitorFeedbackRepository monitorFeedbackRepository;
    @Autowired
    private AdviserFeedbackRepository adviserFeedbackRepository;
    @Autowired
    private AdviserReceivedFormRepository adviserReceivedFormRepository;

    public ResponseEntity<FormFeedback> getMonitorFeedback(String studentID) {
        FormFeedback formFeedback = monitorFeedbackRepository.getMonitorFormFeedback(studentID);
        return new ResponseEntity<>(formFeedback, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity submitFeedback(String monitorID, String formID, String state) {
        if(state.equals(Constant.ACCEPTED_STATE)) {
            adviserReceivedFormRepository.save(new AdviserReceivedForm(formID));
        }
        adviserFeedbackRepository.deleteByUserID(formID);
        monitorReceivedFormRepository.deleteByUserID(formID);
        monitorFeedbackRepository.save(new MonitorFeedback(formID, monitorID, state));
        return new ResponseEntity(HttpStatus.OK);
    }
}
