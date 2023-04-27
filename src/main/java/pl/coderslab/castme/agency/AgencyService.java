package pl.coderslab.castme.agency;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {

    private final AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
