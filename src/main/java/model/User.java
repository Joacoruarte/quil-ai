package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
