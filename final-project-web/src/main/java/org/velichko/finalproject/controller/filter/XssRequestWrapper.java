package org.velichko.finalproject.controller.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ivan Velichko
 * @date 25.08.2021 13:06
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        String[] cleanedValues = null;
        if (values != null) {
            int length = values.length;
            cleanedValues = new String[length];
            for (int i = 0; i < length; i++) {
                cleanedValues[i] = stripXSS(values[i]);
            }
        }
        return cleanedValues;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        String cleanedValue = stripXSS(value);

        LOGGER.debug("Value = " + value + " , cleanedValue  =" + cleanedValue);
        return cleanedValue;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {
        if (value != null){
            value = value.replaceAll("<","");
            value = value.replaceAll(">","");
        }
        return value;
    }
}
