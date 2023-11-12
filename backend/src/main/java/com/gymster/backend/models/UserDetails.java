package com.gymster.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymster.backend.services.MyUserDetailsService;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @Lob
    private byte[] profilePhoto = MyUserDetailsService.getDefaultImage();

    public UserDetails(User user, String email){
        this.user=user;
        this.email=email;
    }

}
