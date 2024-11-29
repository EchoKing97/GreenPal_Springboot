package com.myApp.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "plant_care_tips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantCareTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int knowledgeId;

    private String content;
}