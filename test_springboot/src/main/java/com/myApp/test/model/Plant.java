package com.myApp.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Table(name = "Plants_Info")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plant_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(unique = true)
    private String plant_name;

    private String category;

    private String preferences;

    private Date createdDate;

    @Lob
    private byte[] plant_image;

    public Plant(String plantName, String category, String preferences, User user, byte[] plantImage) {
        this.plant_name = plantName;
        this.category = category;
        this.preferences = preferences;
        this.user = user;
        this.plant_image = plantImage;
    }

    public String getPlantImageBase64() {
        return java.util.Base64.getEncoder().encodeToString(this.plant_image);
    }
}
