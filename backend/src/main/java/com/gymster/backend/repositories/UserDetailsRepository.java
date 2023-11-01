package com.gymster.backend.repositories;

import com.gymster.backend.models.User;
import com.gymster.backend.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

}
