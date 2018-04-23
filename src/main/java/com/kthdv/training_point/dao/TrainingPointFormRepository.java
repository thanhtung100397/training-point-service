package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.TrainingPointForm;
import com.kthdv.training_point.models.response.StudentTrainingPointForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainingPointFormRepository extends JpaRepository<TrainingPointForm, String> {
    TrainingPointForm findByUserID(String userID);

    @Query("select new com.kthdv.training_point.models.response.StudentTrainingPointForm(u, tpf) " +
            "from User u, TrainingPointForm tpf " +
            "where tpf.userID = ?1 and u.id = ?1")
    StudentTrainingPointForm getTrainingPointFormDetail(String userID);
}
