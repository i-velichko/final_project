package org.velichko.finalproject.logic.pool;

import java.util.TimerTask;

/**
 * @author Ivan Velichko
 *
 * The type Timer connection pool check.
 */
public class TimerConnectionPoolCheck extends TimerTask {

    @Override
    public void run() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.poolSizeCheckAndFix();
    }
}
