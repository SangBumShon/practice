package com.sk.skala.walktogether.repository;

import com.sk.skala.walktogether.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);
    Optional<User> findByUserNickname(String nickname);
}
