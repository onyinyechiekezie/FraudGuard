package com.fraudguard.fraudguard.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "agents")
public class Agent extends User{
    private List<String> registeredPoSMachines;
    private int subscriptionFee;
}
