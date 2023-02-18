package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                String end1 = o1.getEndTime().toString();
                String end2 = o2.getEndTime().toString();
                String end3 = end1.replace(":","");
                String end4 = end2.replace(":","");
                if(Integer.valueOf(end3) < Integer.valueOf(end4)){
                    return -1;
                }else if(Integer.valueOf(end3) > Integer.valueOf(end4)){
                    return 1;
                }
                return 0;
            }
        });
        int count = 1;
        String t = calendar.get(0).getEndTime().toString();
        String t1 = t.replace(":","");
        int lastEnd = Integer.valueOf(t1);
        for (int i = 1; i<calendar.size(); i++){
            String p = calendar.get(i).getStartTime().toString();
            String p1 = p.replace(":","");
            int start = Integer.valueOf(p1);
            if (start > lastEnd){
                String q = calendar.get(i).getEndTime().toString();
                String q1 = q.replace(":","");
                lastEnd = Integer.valueOf(q1);
                count++;
            }
        }
        return count;
    }
}
