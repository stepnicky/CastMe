package pl.coderslab.castme.Selftape;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelftapeRepository extends JpaRepository<Selftape, Long> {
    List<Selftape> getByRoleId(Long roleId);
}
