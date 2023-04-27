package pl.coderslab.castme.userrole;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
}
