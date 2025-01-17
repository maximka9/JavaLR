package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.repository.NotificationRulesRepository;
import com.example.model.NotificationRules;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRulesRepository notificationRulesRepository;

    @Autowired
    private JavaMailSender emailSender;

    // Новый метод для отправки email уведомлений по любому изменению
    public void sendEmailNotification(String entityName, Long entityId, String changeDetails) {
        List<NotificationRules> rules = notificationRulesRepository.findByEntityName(entityName);

        for (NotificationRules rule : rules) {
            // Пример отправки email независимо от изменений
            sendEmail(rule.getEmail(), "Change Detected", "Change details for entity " + entityName + " (ID: " + entityId + "): " + changeDetails);
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("sedykh.maksim@mail.ru");  // Укажите явный адрес отправителя
        message.setSubject(subject);
        message.setText(body);

        try {
            emailSender.send(message);
            System.out.println("Email successfully sent to: " + to);  // Логируем успешную отправку
        } catch (Exception e) {
            System.out.println("Error sending email to " + to + ": " + e.getMessage());  // Логируем ошибку
        }
    }
}
