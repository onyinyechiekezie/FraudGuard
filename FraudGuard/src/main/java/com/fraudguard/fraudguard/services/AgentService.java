package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.Agent;

import java.util.List;

public interface AgentService {
    Agent getAgentById(String id);

    List<Agent> getAllAgents();

    Agent saveAgent(Agent agent);

}
