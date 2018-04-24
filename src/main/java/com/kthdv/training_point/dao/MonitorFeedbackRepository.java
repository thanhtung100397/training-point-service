package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.MonitorFeedback;
import com.kthdv.training_point.models.response.FormFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonitorFeedbackRepository extends JpaRepository<MonitorFeedback, String> {
    @Query("select new com.kthdv.training_point.models.response.FormFeedback(u, mf) " +
            "from MonitorFeedback mf, User u " +
            "where mf.userID = ?1 and mf.userID = u.id")
    FormFeedback getMonitorFormFeedback(String userID);

    void deleteByUserID(String userID);
}
