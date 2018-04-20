package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.TrainingPointForm;
import com.kthdv.training_point.models.response.StudentTrainingPointForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainingPointFormRepository extends JpaRepository<TrainingPointForm, String> {
    TrainingPointForm findByUserID(String userID);

    @Query("select new com.kthdv.training_point.models.response.StudentTrainingPointForm(u, tpf) " +
            "from MonitorReceivedForm mr, User u, TrainingPointForm tpf " +
            "where not exists (select 1 from MonitorFeedback mf where mf.userID = mr.userID) " +
            "and u.id = mr.userID " +
            "and tpf.userID = mr.userID")
    StudentTrainingPointForm getTrainingPointFormDetail(String userID);
}
