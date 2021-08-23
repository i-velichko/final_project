package org.velichko.finalproject.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * @author Ivan Velichko
 *
 * The type Lazy wrapper.
 */
public class LazyWrapper {
    private final Logger LOGGER = LogManager.getLogger();
    private final Lock lock = new ReentrantLock();
    private final Supplier<?> supplier;
    private Object singleton;

    private LazyWrapper(Supplier<?> supplier) {
        this.supplier = supplier;
    }

    /**
     * Build wrapper lazy wrapper.
     *
     * @param supplier the supplier
     * @return the lazy wrapper
     */
    public static LazyWrapper buildWrapper(Supplier<?> supplier) {
        return new LazyWrapper(supplier);
    }

    /**
     * Gets singleton.
     *
     * @return the singleton
     */
    public Object getSingleton() {
        if (singleton == null) {
            lock.lock();
            if (singleton == null) {
                singleton = supplier.get();
                LOGGER.info("Lazy Initialization for {}", singleton);
            }
            lock.unlock();
        }
        return singleton;
    }
}
