package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.Agent;
import com.fraudguard.fraudguard.data.repositories.AgentRepository;
import com.fraudguard.fraudguard.exceptions.AgentNotFoundException;
import com.fraudguard.fraudguard.services.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

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

}


