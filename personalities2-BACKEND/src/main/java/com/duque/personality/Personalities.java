package com.duque.personality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personalities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Using IDENTITY for auto-increment
    private Integer id;

    private String name;

    @Column(length = 1000) // Example: Setting the maximum length to 1000 characters.
    private String description;

    private String field; // Field of expertise or domain
    private String image; // URL or path to image
    private Double netWorth; // Example field, feel free to modify as needed

    // Default Constructor (required by JPA)
    public Personalities() {
    }

    // Constructor with fields (optional, but good practice)
    public Personalities(String name, String description, String field, String image, Double netWorth) {
        this.name = name;
        this.description = description;
        this.field = field;
        this.image = image;
        this.netWorth = netWorth;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }
}