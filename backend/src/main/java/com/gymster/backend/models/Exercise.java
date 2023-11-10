package com.gymster.backend.models;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;
    private String name;

    public Exercise(String name){
        this.name=name;
    }
}
