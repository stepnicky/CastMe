package pl.coderslab.castme.agent;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.user.User;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent getAgentByUser(User user) {
        return agentRepository.getByUser(user);
    }

    public void saveAgent(Agent agent) {
        agentRepository.save(agent);
    }
}
