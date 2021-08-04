package org.velichko.finalproject.logic.service.impl;

import org.velichko.finalproject.logic.pool.PropertyLoader;
import org.velichko.finalproject.logic.service.AdmissionScoreCheckService;

import java.net.URL;
import java.util.Properties;

public class AdmissionScoreCheckServiceImpl implements AdmissionScoreCheckService {
    private final URL VERIFICATION_PROPERTIES_PATH = getClass().getClassLoader().getResource("verification.properties");

    @Override
    public boolean checkScore(double studentScore) {
        boolean isAdmission = false;
        Properties properties;
        double admissionScore = 0;
        if (VERIFICATION_PROPERTIES_PATH != null) {
            properties = PropertyLoader.loadPropertiesData(VERIFICATION_PROPERTIES_PATH);
            admissionScore = Double.parseDouble(properties.getProperty("minimal.admission.score"));
        }
        if (studentScore > admissionScore) {
            isAdmission = true;
        }
        return isAdmission;
    }
}
