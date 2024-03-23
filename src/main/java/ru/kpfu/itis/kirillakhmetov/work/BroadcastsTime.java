package ru.kpfu.itis.kirillakhmetov.work;

public class BroadcastsTime implements Comparable {

    private String time;

    public BroadcastsTime(String time) {
        this.time = time;
    }

    public byte hour() {
        return Byte.parseByte(time.substring(0, 2));
    }
    public byte minutes() {
        return Byte.parseByte(time.substring(3, 5));
    }
    public boolean after(BroadcastsTime t) {
        return (this.hour() >= t.hour() && this.minutes() > t.minutes()) ||
                ((this.hour() > t.hour() && this.minutes() >= t.minutes()));
    }

    public boolean before(BroadcastsTime t) {
        return (this.hour() <= t.hour() && this.minutes() < t.minutes()) ||
                ((this.hour() < t.hour() && this.minutes() <= t.minutes()));
    }
    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return this.after(t1) && this.before(t2);
    }

    @Override
    public int compareTo(Object ch) {
        if (ch instanceof ProgramData) {
            ProgramData pd = (ProgramData) ch;
            if (this.after(pd.getTime())) {
                return 1;
            } else if (this.before(pd.getTime())) {
                return -1;
            } else {
                return 0;
            }

        } else {
            throw new IllegalStateException();
        }
    }

    public String getTime() {
        return time;
    }
}
