package pl.coderslab.castme.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class AgencyConverter implements Converter<String, Agency> {
    @Autowired
    private AgencyService agencyService;
    @Override
    public Agency convert(String source) {
        return agencyService.getAgencyById(Long.parseLong(source));
    }
}
