package org.velichko.finalproject.i18n;

import org.velichko.finalproject.logic.exception.ConnectionPoolException;
import org.velichko.finalproject.logic.pool.PropertyLoader;

import java.net.URL;
import java.util.*;

public class I18nManager {

    private static final String SUPPORTED_LOCALES = "supported.locales";
    private static final int COUNTRY_INDEX = 0;
    private static final int LANGUAGE_INDEX = 1;
    private Map<Locale, ResourceBundle> bundleMap = new HashMap<>();

    private I18nManager() {
        List<Locale> localeList = getAllLocales();

        for (Locale locale : localeList) {
            ResourceBundle localization = ResourceBundle.getBundle("localization", locale);
            bundleMap.put(locale, localization);
        }
    }

    private List<Locale> getAllLocales() {
        List<Locale> result = new ArrayList<>();
        try {
            URL resource = getClass().getClassLoader().getResource("common.properties");
            Properties properties = PropertyLoader.loadPropertiesData(resource);
            String property = properties.getProperty(SUPPORTED_LOCALES);
            for (String next : property.split(",")) {
                String country = next.split("-")[COUNTRY_INDEX].trim();
                String language = next.split("-")[LANGUAGE_INDEX].trim();
                result.add(new Locale(country, language));
            }
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Cannot load resource bundle locales");
        }
        return result;
    }

    public String getMassage(String key, Locale locale) {
        return bundleMap.get(locale).getString(key);
    }

    public static I18nManager getInstance() {
        return I18nManager.I18nManagerHolder.HOLDER_INSTANCE;
    }

    private static class I18nManagerHolder {
        public static final I18nManager HOLDER_INSTANCE = new I18nManager();
    }

}
