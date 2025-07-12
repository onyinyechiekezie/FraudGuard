package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.Agent;
import com.fraudguard.fraudguard.data.models.Notification;

import java.util.List;

public interface AgentService {
    Agent getAgentById(String id);

    List<Agent> getAllAgents();

    Agent saveAgent(Agent agent);

    Notification viewNotification(String agent);
}
