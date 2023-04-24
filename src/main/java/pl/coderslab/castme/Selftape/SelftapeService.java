package pl.coderslab.castme.Selftape;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelftapeService {

    private final SelftapeRepository selftapeRepository;

    public SelftapeService(SelftapeRepository selftapeRepository) {
        this.selftapeRepository = selftapeRepository;
    }

    public List<Selftape> getSelftapesByRoleId(Long roleId) {
        return selftapeRepository.getByRoleId(roleId);
    }

    public void deleteSelftapesByRoleId(Long roleId) {
        selftapeRepository.deleteByRoleId(roleId);
    }
    public void saveSelftape(Selftape selftape) {
        selftapeRepository.save(selftape);
    }
}
