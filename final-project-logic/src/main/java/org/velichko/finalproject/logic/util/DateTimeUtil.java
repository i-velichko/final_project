package org.velichko.finalproject.logic.util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Ivan Velichko
 * @date 22.08.2021 15:58
 */
public class DateTimeUtil {

    /**
     * Timestamp to local date time local date time.
     *
     * @param timestamp the timestamp
     * @return the local date time
     * @throws SQLException the sql exception
     */
    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) throws SQLException {
        LocalDateTime result = null;
        if (timestamp != null) {
            result = timestamp.toLocalDateTime();
        }
        return result;
    }
}
