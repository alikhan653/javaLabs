package kz.iitu.javaLabs.repository;

import kz.iitu.javaLabs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
