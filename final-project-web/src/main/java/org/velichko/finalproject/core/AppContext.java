package org.velichko.finalproject.core;

/**
 * @author Ivan Velichko
 *
 * The interface App context.
 */
public interface AppContext {

    /**
     * Gets service.
     *
     * @param <T>    the type parameter
     * @param tClass the t class
     * @return the service
     */
    <T> T getService(Class<T> tClass);
}
