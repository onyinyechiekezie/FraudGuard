package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.Agent;
import com.fraudguard.fraudguard.data.models.Notification;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;

import java.util.List;

public interface AgentService {
    Agent getAgentById(String id);

    List<Agent> getAllAgents();

    Agent saveAgent(Agent agent);

    NotificationResponse viewNotification(String agentId);

    NotificationResponse viewDailyNotification(String agentId);

    List<NotificationResponse> getAgentNotifications(String agentId);
}
