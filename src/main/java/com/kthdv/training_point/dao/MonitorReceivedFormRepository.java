package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.MonitorReceivedForm;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonitorReceivedFormRepository extends JpaRepository<MonitorReceivedForm, String> {
    @Query("select new com.kthdv.training_point.models.response.StudentTrainingPointFormPreview(u, tpf.lastModified) " +
            "from MonitorReceivedForm mr, User u, TrainingPointForm tpf " +
            "where not exists (select 1 from MonitorFeedback mf where mf.userID = mr.userID) " +
            "and u.id = mr.userID " +
            "and tpf.userID = mr.userID")
    List<StudentTrainingPointFormPreview> getAllReceivedStudentTrainingPointForms();

    void deleteByUserID(String userID);
}
