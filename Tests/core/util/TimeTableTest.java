package core.util;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Patrick
 * @since 29.11.2016
 */
public class TimeTableTest {

    @Test
    public void testMinusLocalDates() throws Exception {
        LocalTime currentDate  = LocalTime.now();
        LocalTime previousDate = LocalTime.now();
        previousDate = previousDate.minusHours(1);

        try {
            LocalTime minus = new TimeTable().minus(previousDate, currentDate);
            assert false;
        } catch (DateTimeException ex){
            // Successfully catched the exception
        }
    }

    @Test
    public void testMinusLocalDateTimes() throws Exception {
        LocalDateTime currentDate  = LocalDateTime.now();
        LocalDateTime previousDate = LocalDateTime.now();
        previousDate = previousDate.minusHours(1);

        LocalDateTime minus = new TimeTable().minus(previousDate, currentDate);
    }
}