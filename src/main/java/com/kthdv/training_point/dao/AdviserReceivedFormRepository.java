package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.AdviserReceivedForm;
import com.kthdv.training_point.models.response.StudentTrainingPointFormPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdviserReceivedFormRepository extends JpaRepository<AdviserReceivedForm, String> {
    @Query("select new com.kthdv.training_point.models.response.StudentTrainingPointFormPreview(u, tpf.lastModified) " +
            "from AdviserReceivedForm ar, User u, TrainingPointForm tpf " +
            "where not exists (select 1 from AdviserFeedback af where af.userID = ar.userID) " +
            "and u.id = ar.userID " +
            "and tpf.userID = ar.userID")
    List<StudentTrainingPointFormPreview> getAllReceivedStudentTrainingPointForms();

    void deleteByUserID(String userID);
}
