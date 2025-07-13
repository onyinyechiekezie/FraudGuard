package com.fraudguard.fraudguard.services;


import com.fraudguard.fraudguard.data.enums.AlertLevel;
import com.fraudguard.fraudguard.data.models.Agent;
import com.fraudguard.fraudguard.data.models.Notification;
import com.fraudguard.fraudguard.data.repositories.AgentRepository;
import com.fraudguard.fraudguard.data.repositories.NotificationRepository;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.exceptions.AgentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent getAgentById(String id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + id));
    }

    @Override
    public NotificationResponse viewNotification(String agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + agentId));

        Notification notification = Notification.builder()
                .userId(agent.getId())
                .message("Suspicious activity detected.")
                .alertLevel(AlertLevel.INFO)
                .timestamp(LocalDateTime.now())
                .isRead(false)
                .build();


        notificationRepository.save(notification);

        return new NotificationResponse(
                notification.getMessage(),
                notification.isRead(),
                notification.getAlertLevel(),
                notification.getTimestamp()
        );

    }

    @Override
    public NotificationResponse viewDailyNotification(String agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + agentId));


        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.now();

        List<Notification> todaysAlerts = notificationRepository.findByUserIdAndTimestampBetween(
                agent.getId(), startOfDay, endOfDay
        );

        long totalAlerts = todaysAlerts.size();

        long infoCount = todaysAlerts.stream().filter(n -> n.getAlertLevel() == AlertLevel.INFO).count();
        long warningCount = todaysAlerts.stream().filter(n -> n.getAlertLevel() == AlertLevel.WARNING).count();
        long criticalCount = todaysAlerts.stream().filter(n -> n.getAlertLevel() == AlertLevel.DANGER).count();

        String message = "Daily Summary: " + totalAlerts + " alerts — " +
                infoCount + " INFO, " + warningCount + " WARNING, " + criticalCount + " CRITICAL";

        Notification summary = Notification.builder()
                .userId(agent.getId())
                .message(message)
                .alertLevel(AlertLevel.INFO)
                .timestamp(LocalDateTime.now())
                .isRead(false)
                .build();

        notificationRepository.save(summary);

        return new NotificationResponse(
                summary.getMessage(),
                summary.isRead(),
                summary.getAlertLevel(),
                summary.getTimestamp()
        );

    }

    @Override
    public List<NotificationResponse> getAgentNotifications(String agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + agentId));

        List<Notification> notifications = notificationRepository.findByUserId(agent.getId());

        return notifications.stream()
                .map(notification -> new NotificationResponse(
                        notification.getMessage(),
                        notification.isRead(),
                        notification.getAlertLevel(),
                        notification.getTimestamp()
                ))
                .toList();
    }

}











