package pl.coderslab.castme.agent;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.actor.Actor;
import pl.coderslab.castme.actor.ActorService;
import pl.coderslab.castme.actorrole.ActorRole;
import pl.coderslab.castme.actorrole.ActorRoleService;
import pl.coderslab.castme.agency.Agency;
import pl.coderslab.castme.agency.AgencyService;
import pl.coderslab.castme.casting.Casting;
import pl.coderslab.castme.casting.CastingService;
import pl.coderslab.castme.role.Role;
import pl.coderslab.castme.user.CurrentUser;
import pl.coderslab.castme.user.User;

import java.util.List;

@Controller
@RequestMapping("/agent")
public class AgentController {

    private final AgentService agentService;
    private final AgencyService agencyService;
    private final CastingService castingService;
    private final ActorRoleService actorRoleService;
    private final ActorService actorService;

    public AgentController(AgentService agentService,
                           AgencyService agencyService,
                           CastingService castingService,
                           ActorRoleService actorRoleService,
                           ActorService actorService) {
        this.agentService = agentService;
        this.agencyService = agencyService;
        this.castingService = castingService;
        this.actorRoleService = actorRoleService;
        this.actorService = actorService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser,
                            Model model) {
        Agent agent = agentService.getAgentByUser(customUser.getUser());
        if (agent == null) {
            return "redirect:/agent/form";
        }
        Long agencyId = agent.getAgency().getId();
        List<Casting> castings = castingService.getCastingsByAgencyId(agencyId);
        for(Casting c : castings) {
            for(Role r : c.getRoles()) {
                List<Actor> actors = actorService.getActorsByAgencyIdAndRoleId(agencyId, r.getId());
                model.addAttribute(String.format("actors%s", r.getId()), actors);
            }
        }
        List<ActorRole> actorRoles = actorRoleService.getActorRolesByAgencyId(agencyId);
        model.addAttribute("castings", castings);
        model.addAttribute("actorRoles", actorRoles);
        return "agent/dashboard";
    }
    @GetMapping("/form")
    public String agentForm(Model model) {
        model.addAttribute("agent", new Agent());
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "agent/form";
    }
    @PostMapping("/form")
    public String processAgentForm(Agent agent, @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        agent.setUser(user);
        agentService.saveAgent(agent);
        return "redirect:/agent";
    }
    @GetMapping("/agency/create")
    public String createAgencyForm(Model model) {
        model.addAttribute("agency", new Agency());
        return "agency/form";
    }
    @PostMapping("/agency/create")
    public String createAgency(Agency agency,
                               @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        agencyService.saveAgency(agency);
        Agent agent = new Agent();
        agent.setUser(user);
        agent.setAgency(agency);
        agentService.saveAgent(agent);
        return "redirect:/agent";
    }
}
