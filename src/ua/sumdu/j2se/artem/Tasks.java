package ua.sumdu.j2se.artem;

import java.util.*;

public class Tasks {
    static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end){
//        if(start < 0 || end < 0 || start > end)
//            throw new IllegalArgumentException("Invalid value(s)");

        ArrayTaskList incomingTasks = new ArrayTaskList();
        for (Task task: tasks) {
            if(!task.getStartTime().before(start) && !task.getEndTime().after(end))
                incomingTasks.add(task);
        }
        return incomingTasks;
    }

    static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end){

        TreeMap<Date, Set<Task>> calendar = new TreeMap<Date, Set<Task>>();
        Iterable<Task> inc = incoming(tasks, start, end);
        for (Task task : inc) {
            Date tmp = task.nextTimeAfter(start);
            while(tmp != null && tmp.after(end)) {
                if (calendar.containsKey(tmp)) {
                    calendar.get(tmp).add(task);
                } else {
                    Set<Task> setOfTasks = new HashSet<Task>();
                    setOfTasks.add(task);
                    calendar.put(tmp, setOfTasks);
                }
                tmp = task.nextTimeAfter(tmp);
            }
        }
        return calendar;
    }
}
