package com.example.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.service.EventLogService;
import com.example.service.NotificationService;

@Component
public class MessageListener {

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private NotificationService notificationService;

    @JmsListener(destination = "changeQueue")
    public void onMessage(String message) {
        // Логика обработки полученного сообщения
        System.out.println("Received message: " + message);

        // Записываем сообщение в таблицу логирования
        eventLogService.logEvent(message);

        // Вне зависимости от типа изменения, отправляем email уведомление
        String entityName = "Employee";  // Извлекаем название сущности
        Long entityId = extractEntityIdFromMessage(message); // Извлекаем ID сущности
        String changeDetails = message;

        // Отправляем email уведомление
        notificationService.sendEmailNotification(entityName, entityId, changeDetails);
    }

    private Long extractEntityIdFromMessage(String message) {
        // Пример извлечения ID из сообщения (например, из строки "Employee saved: 9")
        String[] parts = message.split(":");
        if (parts.length > 1) {
            return Long.valueOf(parts[1].trim());
        }
        return null;
    }
}
