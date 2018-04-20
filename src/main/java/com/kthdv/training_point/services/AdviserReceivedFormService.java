package com.kthdv.training_point.services;

import com.kthdv.training_point.dao.AdviserReceivedFormRepository;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviserReceivedFormService {
    @Autowired
    private AdviserReceivedFormRepository adviserReceivedFormRepository;

    public ResponseEntity<List<StudentTrainingPointFormPreview>> getAllReceivedStudentTrainingPointForm() {
        return new ResponseEntity<>(adviserReceivedFormRepository.getAllReceivedStudentTrainingPointForms(), HttpStatus.OK);
    }
}
