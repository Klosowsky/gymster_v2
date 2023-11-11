package com.gymster.backend.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDetailsDTO {
    private MultipartFile file = null;
    private String email;

}
