package ua.sumdu.j2se.artem;

import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end){
        if(start.before(new Date(0)) || end.before(start))
            throw new IllegalArgumentException("Invalid start or end");
        if ((tasks == null) || (start == null) || (end == null))
            throw new IllegalArgumentException("Null argument");

        TaskList incomingList = null;
        if ((tasks instanceof ArrayTaskList)) {
            incomingList = new ArrayTaskList();
        } else {
            incomingList = new LinkedTaskList();
        }
        for (Task task : tasks) {
            if (task.isActive()) {
                Date data = task.nextTimeAfter(start);
                if ((data != null) && (data.compareTo(end) <= 0)) {
                    incomingList.add(task);
                }
            }
        }
        return incomingList;
    }

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end){
        if ((tasks == null) || (start == null) || (end == null)) {
            throw new IllegalArgumentException("null argument");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("From can not be greater than to");
        }
        TreeMap<Date, Set<Task>> calendar = new TreeMap<Date, Set<Task>>();
        Iterable<Task> incomingList = incoming(tasks, start, end);
        for (Task task : incomingList) {
            Date keyDate = task.nextTimeAfter(start);
            while(keyDate != null && keyDate.compareTo(end) <= 0) {
                if (calendar.containsKey(keyDate)) {
                    calendar.get(keyDate).add(task);
                } else {
                    Set<Task> tasksSet = new HashSet<Task>();
                    tasksSet.add(task);
                    calendar.put(keyDate, tasksSet);
                }
                keyDate = task.nextTimeAfter(keyDate);
            }
        }
        return calendar;
    }
}
