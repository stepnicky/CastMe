package pl.coderslab.castme.Role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRolesByCasting(Long id) {
        return roleRepository.getAllByCasting(id);
    }

    public void addNewRole(Role role) {
        roleRepository.save(role);
    }
}
