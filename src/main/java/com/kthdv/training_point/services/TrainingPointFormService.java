package com.kthdv.training_point.services;

import com.kthdv.training_point.dao.*;
import com.kthdv.training_point.models.entity.MonitorReceivedForm;
import com.kthdv.training_point.models.entity.TrainingPointForm;
import com.kthdv.training_point.models.response.StudentTrainingPointForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class TrainingPointFormService {
    @Autowired
    private TrainingPointFormRepository trainingPointFormRepository;
    @Autowired
    private MonitorReceivedFormRepository monitorReceivedFormRepository;
    @Autowired
    private MonitorFeedbackRepository monitorFeedbackRepository;
    @Autowired
    private AdviserFeedbackRepository adviserFeedbackRepository;
    @Autowired
    private AdviserReceivedFormRepository adviserReceivedFormRepository;

    public ResponseEntity<StudentTrainingPointForm> getTrainingPointFormDetail(String userID) {
        return new ResponseEntity<>(trainingPointFormRepository.getTrainingPointFormDetail(userID), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity submitForm(String userID, Map<String, Integer> formData) {
        TrainingPointForm trainingPointFormFound = trainingPointFormRepository.findByUserID(userID);
        if(trainingPointFormFound == null) {
            trainingPointFormFound = new TrainingPointForm(userID, formData);
        } else {
            trainingPointFormFound.update(formData);
            monitorFeedbackRepository.deleteByUserID(userID);
            adviserFeedbackRepository.deleteByUserID(userID);
            adviserReceivedFormRepository.deleteByUserID(userID);
        }
        trainingPointFormRepository.save(trainingPointFormFound);
        monitorReceivedFormRepository.save(new MonitorReceivedForm(userID));
        return new ResponseEntity(HttpStatus.OK);
    }

    public boolean isTrainingPointFormExist(String id) {
        return trainingPointFormRepository.existsById(id);
    }
}
