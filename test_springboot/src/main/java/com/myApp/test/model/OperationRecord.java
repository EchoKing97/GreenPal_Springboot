package com.myApp.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "operation_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int plantId;

    private String plantName;

    private String operation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date operationTime;
}