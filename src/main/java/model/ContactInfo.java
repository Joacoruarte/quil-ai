package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ContactInfo")
public class ContactInfo {
    @Id
    @GeneratedValue
    private int id;

    private String email;
    private String phoneNumber;
    @ManyToOne
    private User user;
}