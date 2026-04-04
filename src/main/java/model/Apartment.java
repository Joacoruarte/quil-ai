package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Apartment")
public class Apartment{
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Embedded
    private Address adress;

    private long price;
    private int bedrooms;
    private int bathrooms;
    private boolean balcony;
    private boolean professionalUse;
    private Float size;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int ageOfBuilding;
    private boolean rearFacing;

    @CollectionTable
    private List<Amenity> amenities;

    @ManyToOne
    private User publisher;

}
