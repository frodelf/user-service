package com.example.userservice.entity;

import com.example.userservice.entity.users.Notary;
import com.example.userservice.entity.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfRooms;
    private Integer fullArea;
    private Integer kitchenArea;
    private Boolean thereIsBalcony;
    private Boolean thereIsMortgage;
    private String calculationOptions;
    private Integer agencyCommission;
    private String description;
    private String reason;
    private Integer price;
    private Integer floor;
    private LocalDate updatedDate;

    private StatusState statusState;
    private Appointment appointment;
    private FoundationDocument foundationDocument;
    private LivingCondition livingCondition;
    private Layout layout;
    private Heating heating;

    @ElementCollection
    private List<String> images;
    @ManyToOne
    @JoinColumn(name = "corps_id", referencedColumnName = "id")
    private Corps corps;
    @ManyToOne
    @JoinColumn(name = "notary_id", referencedColumnName = "id")
    private Notary notary;
}