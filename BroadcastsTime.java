public class BroadcastsTime implements Comparable<BroadcastsTime> {

    byte hour;
    byte minutes;

    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public int compareTo(BroadcastsTime o) {
        if (this.hour == o.hour)    {
            return Byte.compare(this.minutes, o.minutes);
        } else {
            return Byte.compare(this.hour, o.hour);
        }
    }

    public boolean after(BroadcastsTime t) {
        return this.compareTo(t) > 0;
    }

    public boolean before(BroadcastsTime t) {
        return this.compareTo(t) < 0;
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return this.after(t1) && this.before(t2);
    }

    public byte getHour() {
        return hour;
    }

    public byte getMinutes() {
        return minutes;
    }
}