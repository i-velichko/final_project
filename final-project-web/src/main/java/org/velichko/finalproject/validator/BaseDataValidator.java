package org.velichko.finalproject.validator;

import java.util.Map;

public interface BaseDataValidator {

    Map<String, String> checkValues(Map<String, String> data, String locale);
}
