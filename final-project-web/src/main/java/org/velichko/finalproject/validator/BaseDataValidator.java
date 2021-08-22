package org.velichko.finalproject.validator;

import org.velichko.finalproject.logic.exception.ServiceException;

import java.util.Map;

public interface BaseDataValidator {

    Map<String, String> checkValues(Map<String, String> data, String locale) throws ServiceException;
}
