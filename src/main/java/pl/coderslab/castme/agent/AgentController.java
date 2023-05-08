package pl.coderslab.castme.agent;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.agency.Agency;
import pl.coderslab.castme.agency.AgencyService;
import pl.coderslab.castme.user.CurrentUser;
import pl.coderslab.castme.user.User;

@Controller
@RequestMapping("/agent")
public class AgentController {

    private final AgentService agentService;
    private final AgencyService agencyService;

    public AgentController(AgentService agentService,
                           AgencyService agencyService) {
        this.agentService = agentService;
        this.agencyService = agencyService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser) {
        Agent agent = agentService.getAgentByUser(customUser.getUser());
        if (agent == null) {
            return "redirect:/agent/form";
        }
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
