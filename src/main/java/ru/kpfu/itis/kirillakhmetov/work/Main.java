package ru.kpfu.itis.kirillakhmetov.work;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Считывание файла
        Scanner s = null;
        try {
            s = new Scanner(new File("schedule.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> list = new ArrayList<>();
        while (s.hasNext()){
            list.add(s.nextLine());
        }
        s.close();

        // Заполнение Map и List
        List<ProgramData> listPrograms = new ArrayList<>();
        Map<BroadcastsTime, List<ProgramData>> mapPrograms = new HashMap<>();

        String currentChannel = null;
        List<ProgramData> temp = null;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(0) == '#') {
                currentChannel = list.get(i).substring(1);
            } else {
                BroadcastsTime time = new BroadcastsTime(list.get(i));
                String name = list.get(++i);
                ProgramData pd = new ProgramData(currentChannel, time, name);

                listPrograms.add(pd);
                List<ProgramData> tempPd = new ArrayList<>();

                if (mapPrograms.get(time) == null) {
                    mapPrograms.get(time).add(pd);
                } else {
                    tempPd.add(pd);
                    mapPrograms.put(time, tempPd);
                }
            }
        }

        for (ProgramData pd : sorted(listPrograms)) {
            System.out.println(pd.toString());
        }

        for (ProgramData pd : ChannelJustNow(new BroadcastsTime("14:00"), listPrograms)) {
            System.out.println(pd.toString());
        }

        for (ProgramData pd : findByBetweenTime(new BroadcastsTime("14:00"), new BroadcastsTime("20:00"), listPrograms)) {
            System.out.println(pd.toString());
        }

        for (ProgramData pd : findByChannelNow("НТВ", mapPrograms)) {
            System.out.println(pd.toString());
        }

    }
    public static List<ProgramData> sorted(List<ProgramData> lst) {
        Collections.sort(lst);
        return lst;
    }

    public static List<ProgramData> ChannelJustNow(BroadcastsTime time, List<ProgramData> lst) {
        List<ProgramData> list = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            while (time.after(lst.get(i).getTime())) {
                i++;
                list.add(lst.get(i));
            }
            list.add(lst.get(i));
        }

        return list;
    }

    public static List<ProgramData> findByBetweenTime(BroadcastsTime t1, BroadcastsTime t2, List<ProgramData> programs){
        List<ProgramData> findProgramms = new ArrayList<>();
        for(ProgramData program: programs){
            if (program.getTime().between(t1, t2)){
                findProgramms.add(program);

            }
        }
        return findProgramms;
    }

    public static List<ProgramData> findByName(String str, List<ProgramData> allPrograms){
        List<ProgramData> findProgramms = new ArrayList<>();
        for(ProgramData program: allPrograms){
            if (program.getName().startsWith(str)){
                findProgramms.add(program);

            }
        }
        return findProgramms;
    }

    public static List<ProgramData> findByChannelNow(String str, Map<BroadcastsTime, List<ProgramData>> allPrograms){
        List<ProgramData> findProgramms = allPrograms.get(str);
        System.out.println(findProgramms);
        List<ProgramData> sendPrograms = new ArrayList<>();
        Date date = new Date();
        int time = date.getTime();
        for(ProgramData program: findProgramms){
            if (program.getTime() == time){
                sendPrograms.add(program);

            }
        }
        return sendPrograms;
    }
}