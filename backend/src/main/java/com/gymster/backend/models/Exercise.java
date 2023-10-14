package com.gymster.backend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Exercise {
    @Id
    private Long id;
    private String name;
}
