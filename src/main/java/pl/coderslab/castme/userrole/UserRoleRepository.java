package pl.coderslab.castme.userrole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserRole(String userRole);
}
