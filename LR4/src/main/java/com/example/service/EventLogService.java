package com.example.service;

import com.example.model.EventLog;
import com.example.repository.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    public void logEvent(String message) {
        // Создаем запись в таблице с информацией об изменении
        EventLog eventLog = new EventLog();
        eventLog.setMessage(message);
        eventLog.setTimestamp(System.currentTimeMillis());  // Записываем время изменения

        eventLogRepository.save(eventLog); // Сохраняем в базе данных
    }
}
