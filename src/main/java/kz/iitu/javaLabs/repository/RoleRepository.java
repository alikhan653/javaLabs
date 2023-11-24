package kz.iitu.javaLabs.repository;


import kz.iitu.javaLabs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
