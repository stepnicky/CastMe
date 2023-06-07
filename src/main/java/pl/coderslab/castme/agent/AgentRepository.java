package pl.coderslab.castme.agent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.user.User;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent getByUser(User user);
}
