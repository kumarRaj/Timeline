package com.project.timeline.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String emailId;

    private String phoneNumber;

    private Date dateOfBirth;

    public User(String firstName, String lastName, String emailId, String phoneNumber, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

}
