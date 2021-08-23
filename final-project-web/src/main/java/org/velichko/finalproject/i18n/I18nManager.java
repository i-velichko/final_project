package org.velichko.finalproject.i18n;

import org.velichko.finalproject.logic.pool.PropertyLoader;

import java.net.URL;
import java.util.*;


/**
 * @author Ivan Velichko
 *
 * The type 18 n manager.
 */
public class I18nManager {

    private static final String SUPPORTED_LOCALES = "supported.locales";
    private static final String DEFAULT_LOCALE = "ru-RU";
    private final Map<String, ResourceBundle> bundleMap = new HashMap<>();

    /**
     * Instantiates a new 18 n manager.
     */
    public I18nManager() {
        List<String> localeList = getAllLocales();

        for (String locale : localeList) {
            ResourceBundle localization = ResourceBundle.getBundle("localization", Locale.forLanguageTag(locale));
            bundleMap.put(locale, localization);
        }
    }

    private List<String> getAllLocales() {
        List<String> result = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource("common.properties");
        Properties properties = PropertyLoader.loadPropertiesData(resource);
        String property = properties.getProperty(SUPPORTED_LOCALES);
        for (String next : property.split(",")) {
            result.add(next.trim());
        }

        return result;
    }

    /**
     * Gets massage.
     *
     * @param key    the key
     * @param locale the locale
     * @return the massage
     */
    public String getMassage(String key, String locale) {
        if (locale == null) {
            locale = DEFAULT_LOCALE;
        }
        return bundleMap.get(locale).getString(key);
    }
}
