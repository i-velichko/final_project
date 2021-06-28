package org.velichko.finalproject.logic.pool;

import java.util.TimerTask;

public class TimerConnectionPoolCheck extends TimerTask {

    @Override
    public void run() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.poolSizeCheck();
    }
}
