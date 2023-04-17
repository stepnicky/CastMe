package pl.coderslab.castme.FeatureSet;

import org.springframework.stereotype.Service;

@Service
public class FeatureSetService {

    private final FeatureSetRepository featureSetRepository;

    public FeatureSetService(FeatureSetRepository featureSetRepository) {
        this.featureSetRepository = featureSetRepository;
    }

    public void createFeatureSet(FeatureSet featureSet) {
        featureSetRepository.save(featureSet);
    }
}
