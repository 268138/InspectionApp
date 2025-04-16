package org.example.inspectionapplication.entity;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inspection_event")
public class InspectionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate inspectionDate;
    private String inspectionResult;
    private String notes;
    private LocalDate nextInspectionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_center_id")
    private InspectionCenter inspectionCenter;

}