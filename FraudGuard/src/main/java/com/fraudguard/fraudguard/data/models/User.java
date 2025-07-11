package com.fraudguard.fraudguard.data.models;

import com.fraudguard.fraudguard.data.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
@NoArgsConstructor
public abstract class User {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phonenumber;
    private int age;
    private String location;
    private UserRole role;
    private String image;
    private String sessionToken;

}
