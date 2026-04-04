package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Image")
public class ApartmentImage {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String path;
    private String label;

    @ManyToOne
    private Apartment apartment;
}
