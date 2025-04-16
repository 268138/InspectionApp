package org.example.inspectionapplication.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inspection_center")
public class InspectionCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "inspectionCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionEvent> inspectionEvents = new ArrayList<>();

}