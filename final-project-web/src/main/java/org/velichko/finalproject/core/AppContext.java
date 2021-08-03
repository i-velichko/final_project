package org.velichko.finalproject.core;

public interface AppContext {

    <T> T getService(Class<T> tClass);
}
