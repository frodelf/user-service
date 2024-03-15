package com.example.userservice.entity.users;

import com.example.userservice.entity.Building;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Builder extends User {
    @OneToMany(mappedBy = "builder")
    private List<Building> buildings;
}