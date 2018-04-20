package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.AdviserFeedback;
import com.kthdv.training_point.models.response.FormFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdviserFeedbackRepository extends JpaRepository<AdviserFeedback, String> {
    @Query("select new com.kthdv.training_point.models.response.FormFeedback(u, af) " +
            "from AdviserFeedback af, User u " +
            "where af.adviserID = u.id")
    List<FormFeedback> getAllAdviserFeedback();

    void deleteByUserID(String userID);
}
