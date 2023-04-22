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
    public FeatureSet getFeatureSetByRoleId(Long roleId) {
        return featureSetRepository.getByRoleId(roleId);
    }
    public void updateFeatureSet(FeatureSet featureSet) {
        featureSetRepository.save(featureSet);
    }
    public FeatureSet getFeatureSetById(Long id) {
        return featureSetRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    public void deleteFeatureSet(FeatureSet featureSet) {
        featureSetRepository.delete(featureSet);
    }
}
