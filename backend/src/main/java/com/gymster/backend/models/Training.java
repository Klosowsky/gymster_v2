package com.gymster.backend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trainings")
public class Training {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    private String title;

    private String description;
    private int likes;
    private int dislikes;



}
