package com.example.repository;

import com.example.model.NotificationRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRulesRepository extends JpaRepository<NotificationRules, Long> {

    List<NotificationRules> findByEntityName(String entityName);
}
