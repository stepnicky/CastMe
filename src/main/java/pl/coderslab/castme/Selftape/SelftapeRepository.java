package pl.coderslab.castme.Selftape;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface SelftapeRepository extends JpaRepository<Selftape, Long> {
    List<Selftape> getByRoleId(Long roleId);
    @Modifying
    @Transactional
    void deleteByRoleId(Long roleId);
}
