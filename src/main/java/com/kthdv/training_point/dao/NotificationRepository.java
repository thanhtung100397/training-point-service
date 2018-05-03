package com.kthdv.training_point.dao;

import com.kthdv.training_point.models.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,String> {
}
