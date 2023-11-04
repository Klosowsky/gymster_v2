package com.gymster.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {


    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(mappedBy = "userDetails")
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String email;

    private String photo = "default_profile.jpg";

    public UserDetails(User user, String email){
        this.user=user;
        this.email=email;
    }


}
