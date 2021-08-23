package org.velichko.finalproject.validator;

import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.Map;

/**
 * @author Ivan Velichko
 *
 * The interface Base data validator.
 */
public interface BaseDataValidator {
    /**
     * Check values map.
     *
     * @param data   the data
     * @param locale the locale
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<String, String> checkValues(Map<String, String> data, String locale) throws ServiceException;
}
