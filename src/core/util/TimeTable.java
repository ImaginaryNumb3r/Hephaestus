package core.util;

import com.sun.istack.internal.NotNull;
import core.util.contracts.Contract;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Patrick
 * @since 29.11.2016
 */
public class TimeTable {

    public LocalTime minus(@NotNull LocalTime time1, @NotNull LocalTime time2){
        return minusTime(TimeProxy.toProxy(time1), TimeProxy.toProxy(time2)).toTime();
    }

    public LocalDateTime minus(@NotNull LocalDateTime time1, @NotNull LocalDateTime time2){
        // Time
        ProtoTime protoTime = minusTime(TimeProxy.toProxy(time1), TimeProxy.toProxy(time2));
        // Date
        int days   = time1.getDayOfMonth() - time2.getDayOfMonth();
        int months = time1.getMonthValue() - time2.getMonthValue();
        int years  = time1.getYear() - time2.getYear();

        // TODO: recognize negative times 

        throw new UnsupportedOperationException("This operation has yet to be finished!");
    }

    public ProtoTime minusTime(@NotNull TimeProxy time1, @NotNull TimeProxy time2){
        Contract.checkNull(time1, time2);

        int nanos   = time1.getNano() - time2.getNano();
        int seconds = time1.getSecond() - time2.getSecond();
        int minutes = time1.getMinute() - time2.getMinute();
        int hours   = time1.getHour() - time2.getHour();

        return new ProtoTime(nanos, seconds, minutes, hours);
    }


    private class ProtoTime {
        private int _nanos;
        private int _seconds;
        private int _minutes;
        private int _hours;

        public ProtoTime(int nanos, int seconds, int minutes, int hours) {
            _nanos = nanos;
            _seconds = seconds;
            _minutes = minutes;
            _hours = hours;
        }

        public LocalTime toTime() {
            return LocalTime.of(_nanos, _seconds, _minutes, _hours);
        }
    }

    private class ProtoDate{
        private int _dayOfMonth;
        private int _month;
        private int _year;

        public ProtoDate(int dayOfMonth, int month, int year) {
            _dayOfMonth = dayOfMonth;
            _month = month;
            _year = year;
        }

        public LocalDate toTime(){
            return LocalDate.of(_dayOfMonth, _month, _year);
        }
    }

    //<editor-fold desc="Interfaces">

    private interface DateProxy{
        int getDayOfMonth();
        int getMonth();
        int getYear();

        static DateProxy toProxy(LocalDate date){
            return new DateProxy() {
                public int getDayOfMonth() {
                    return date.getDayOfMonth();
                }
                public int getMonth() {
                    return date.getMonthValue();
                }
                public int getYear() {
                    return date.getYear();
                }
            };
        }

        default LocalDate toDate(){
            return LocalDate.of(getYear(), getMonth(), getDayOfMonth());
        }
    }

    private interface TimeProxy{
        int getNano();
        int getSecond();
        int getMinute();
        int getHour();

        static TimeProxy toProxy(LocalTime time){
            return new TimeProxy() {
                public int getNano() {
                    return time.getNano();
                }
                public int getSecond() {
                    return time.getSecond();
                }
                public int getMinute() {
                    return time.getMinute();
                }
                public int getHour() {
                    return time.getHour();
                }
            };
        }

        static TimeProxy toProxy(LocalDateTime time){
            return toProxy(time.toLocalTime());
        }

        default LocalTime toTime(){
            return LocalTime.of(getNano(), getSecond(), getMinute(), getHour());
        }
    }

    private interface DateTimeProxy extends TimeProxy, DateProxy{

        default LocalDateTime toLocalDate(){
            return LocalDateTime.of(toDate(), toTime());
        }
    }
    //</editor-fold>
}