package com.gymster.backend.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestBody {
    private Long Id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
