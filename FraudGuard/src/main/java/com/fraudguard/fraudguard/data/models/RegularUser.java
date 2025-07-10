package com.fraudguard.fraudguard.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "regular users")
public class RegularUser extends User{

    private int subscriptionFee;
}
