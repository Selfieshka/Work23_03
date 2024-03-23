package ru.kpfu.itis.kirillakhmetov.work;

public class ProgramData implements Comparable {
    private String channel;
    private BroadcastsTime time;
    private String name;

    public ProgramData(String channel, BroadcastsTime time, String name) {
        this.channel = channel;
        this.time = time;
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProgramData{" +
                "channel='" + channel + '\'' +
                ", time='" + time.getTime() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object ch) {
        return this.channel.compareTo(((ProgramData) ch).getChannel());
    }
}
