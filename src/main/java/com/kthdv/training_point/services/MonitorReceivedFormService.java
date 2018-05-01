package com.kthdv.training_point.services;

import com.kthdv.training_point.dao.MonitorReceivedFormRepository;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorReceivedFormService {
    @Autowired
    private MonitorReceivedFormRepository monitorReceivedFormRepository;

    public ResponseEntity<List<StudentTrainingPointFormPreview>> getAllReceivedStudentTrainingPointForms() {
        return new ResponseEntity<>(monitorReceivedFormRepository
                .getAllReceivedStudentTrainingPointForms(), HttpStatus.OK);
    }
}
